package ru.sooslick.qa.steps.browser;

import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import ru.sooslick.qa.core.NumberComparisonMethod;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.assertions.StringVerifier;
import ru.sooslick.qa.core.helper.DataGeneratorsHelper;
import ru.sooslick.qa.core.helper.HtmlElementHelper;
import ru.sooslick.qa.core.helper.ItemListHelper;
import ru.sooslick.qa.core.repeaters.Repeat;
import ru.sooslick.qa.pagemodel.actions.ActionType;
import ru.sooslick.qa.pagemodel.annotations.Context;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

import java.util.List;
import java.util.stream.Collectors;

public class ItemListForEachSteps {

    @Context
    private ScenarioContext context;

    @Then("Each item in list {element} has following elements")
    public void checkListItemsStructure(HtmlElement listElement, List<String> elementNames) {
        ItemListHelper.validateListItems(listElement, elementNames);
        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            // TODO: group assertion? instead of foreach. Same for each "each" steps
            listItems.forEach(li -> elementNames.forEach(name -> {
                HtmlElement targetElement = HtmlElementHelper.findElementByName(li, name);
                Assertions.assertTrue(targetElement.isDisplayed(), "Element '" + targetElement.getName() + "' is not visible");
            }));
        });
    }

    @Then("Each {string} in list {element} has no attribute {string}")
    public void checkListItemsAttribute(String listItemName, HtmlElement listElement, String attrName) {
        ItemListHelper.validateListItem(listElement, listItemName);
        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            listItems.forEach(li -> {
                HtmlElement targetElement = HtmlElementHelper.findElementByName(li, listItemName);
                Assertions.assertNull(targetElement.getAttribute(attrName));
            });
        });
    }

    @Then("Each {string} in list {element} has a text {stringVerifier}")
    public void checkListItemsText(String listItemName, HtmlElement listElement, StringVerifier expectedValue) {
        ItemListHelper.validateListItem(listElement, listItemName);
        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            listItems.forEach(li -> {
                HtmlElement targetElement = HtmlElementHelper.findElementByName(li, listItemName);
                String actualValue = targetElement.getText();
                expectedValue.test(actualValue);
            });
        });
    }

    @Then("Each {string} in list {element} has a CSS-property {string} with value {stringVerifier}")
    public void checkListItemsCssProperty(String listItemName, HtmlElement listElement, String propertyName, StringVerifier expectedValue) {
        ItemListHelper.validateListItem(listElement, listItemName);
        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            listItems.forEach(li -> {
                HtmlElement targetElement = HtmlElementHelper.findElementByName(li, listItemName);
                String actualValue = targetElement.getCssValue(propertyName);
                expectedValue.test(actualValue);
            });
        });
    }

    @Then("Each {string} in list {element} has a CSS-property {string} with value {stringVerifier} when hovered")
    public void checkListItemsHoveredCssProperty(String listItemName, HtmlElement listElement, String propertyName, StringVerifier expectedValue) {
        ItemListHelper.validateListItem(listElement, listItemName);
        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            listItems.forEach(li -> {
                HtmlElement targetElement = HtmlElementHelper.findElementByName(li, listItemName);
                targetElement.triggerAction(ActionType.MOUSE_OVER);
                String actualValue = targetElement.getCssValue(propertyName);
                expectedValue.test(actualValue);
            });
        });
    }

    @Then("Each {string} in list {element} have an alternating CSS-property {string} from the following list")
    public void checkListItemsAlternatingCssProperty(String listItemName, HtmlElement listElement, String propertyName, List<String> expectedValuesRaw) {
        ItemListHelper.validateListItem(listElement, listItemName);
        List<StringVerifier> expectedValues = expectedValuesRaw.stream()
                .map(s -> DataGeneratorsHelper.processString(s, context))
                .map(StringVerifier::new)
                .collect(Collectors.toList());

        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            int elementNumber = 0;
            for (HtmlElement li : listItems) {
                if (elementNumber >= expectedValues.size())
                    elementNumber = 0;
                StringVerifier verifier = expectedValues.get(elementNumber++);
                HtmlElement targetElement = HtmlElementHelper.findElementByName(li, listItemName);
                String actualValue = targetElement.getCssValue(propertyName);
                verifier.test(actualValue);
            }
        });
    }

    @Then("Each {string} in list {element} has a width {numberComparisonMethod} {dataGenerator} pixels")
    public void checkListItemsWidth(String listItemName, HtmlElement listElement, NumberComparisonMethod method, String expectedWidthRaw) {
        ItemListHelper.validateListItem(listElement, listItemName);
        int expectedWidth = Integer.parseInt(expectedWidthRaw);
        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            listItems.forEach(li -> {
                HtmlElement targetElement = HtmlElementHelper.findElementByName(li, listItemName);
                int actualWidth = targetElement.getSize().getWidth();
                method.test(expectedWidth, actualWidth);
            });
        });
    }
}
