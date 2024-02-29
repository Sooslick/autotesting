package ru.sooslick.qa.steps.browser;

import io.cucumber.java.en.Then;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.assertions.StringVerifier;
import ru.sooslick.qa.core.helper.DataGeneratorsHelper;
import ru.sooslick.qa.core.helper.HtmlElementHelper;
import ru.sooslick.qa.core.page.HtmlElementBuilder;
import ru.sooslick.qa.core.page.PageFieldDecorator;
import ru.sooslick.qa.core.repeaters.Repeat;
import ru.sooslick.qa.pagemodel.actions.ActionType;
import ru.sooslick.qa.pagemodel.annotations.Context;
import ru.sooslick.qa.pagemodel.components.Component;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemListSteps {

    @Context
    private ScenarioContext context;

    @Then("List {element} consists of items, where {string} has text")
    public void checkListItemsStrict(HtmlElement listElement, String listItemName, List<String> expectedItemsRaw) {
        List<String> expectedItems = expectedItemsRaw.stream()
                .map(s -> DataGeneratorsHelper.processString(s, context))
                .collect(Collectors.toList());

        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = getListItems(listElement);
            List<String> actualItems = listItems.stream()
                    .map(li -> HtmlElementHelper.findElementByName(li, listItemName))
                    .filter(HtmlElement::isDisplayed)
                    .map(target -> (String) target.triggerAction(ActionType.GET_TEXT))
                    .collect(Collectors.toList());
            Assertions.assertIterableEquals(expectedItems, actualItems);
        });
    }

    @Then("Each item in list {element} has following elements")
    public void checkListItemsStructure(HtmlElement listElement, List<String> elementNames) {
        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = getListItems(listElement);
            listItems.forEach(li -> elementNames.forEach(name -> {
                HtmlElement targetElement = HtmlElementHelper.findElementByName(li, name);
                targetElement.triggerAction(ActionType.CHECK_ELEMENT_VISIBLE);
            }));
        });
    }

    @Then("Each {string} in list {element} has a CSS-property {string} with value {dataGenerator}")
    public void checkListItemsCssProperty(String listItemName, HtmlElement listElement, String propertyName, String propertyValue) {
        StringVerifier verifier = new StringVerifier(propertyValue);
        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = getListItems(listElement);
            listItems.forEach(li -> {
                HtmlElement targetElement = HtmlElementHelper.findElementByName(li, listItemName);
                String actualValue = targetElement.getCssValue(propertyName);
                verifier.test(actualValue);
            });
        });
    }

    @Then("Each {string} in list {element} has a CSS-property {string} with value {dataGenerator} when hovered")
    public void checkListItemsHoveredCssProperty(String listItemName, HtmlElement listElement, String propertyName, String propertyValue) {
        StringVerifier verifier = new StringVerifier(propertyValue);
        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = getListItems(listElement);
            listItems.forEach(li -> {
                HtmlElement targetElement = HtmlElementHelper.findElementByName(li, listItemName);
                targetElement.triggerAction(ActionType.MOUSE_OVER);
                String actualValue = targetElement.getCssValue(propertyName);
                verifier.test(actualValue);
            });
        });
    }

    @SneakyThrows
    private List<HtmlElement> getListItems(HtmlElement listContainer) {
        List<WebElement> listItemsRaw = listContainer.findElements(listContainer.getComponentLocator(Component.LI_ELEMENT));
        List<HtmlElement> result = new LinkedList<>();
        for (WebElement liElement : listItemsRaw) {
            HtmlElement wrapperElement = new HtmlElementBuilder(listContainer.getComponentType(Component.LI_ELEMENT))
                    .webDriver(listContainer.getWrappedDriver())
                    .parent(liElement)
                    .locator(By.xpath("./."))   // kinda weird but i can't control over li item locator
                    .build();
            FieldDecorator decorator = new PageFieldDecorator(
                    listContainer.getWrappedDriver(),
                    wrapperElement, wrapperElement);
            PageFactory.initElements(decorator, wrapperElement);
            result.add(wrapperElement);
        }
        return result;
    }
}
