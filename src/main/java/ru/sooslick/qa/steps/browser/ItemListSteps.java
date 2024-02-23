package ru.sooslick.qa.steps.browser;

import io.cucumber.java.en.Then;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.generator.DataGenerators;
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
                .map(s -> DataGenerators.processString(s, context))
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
