package ru.sooslick.qa.steps.browser;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import ru.sooslick.qa.core.Alignment;
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

import java.util.Collection;
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
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            List<String> actualItems = listItems.stream()
                    .map(li -> HtmlElementHelper.findElementByName(li, listItemName))
                    .filter(HtmlElement::isDisplayed)
                    .map(target -> (String) target.triggerAction(ActionType.GET_TEXT))
                    .collect(Collectors.toList());
            Assertions.assertIterableEquals(expectedItems, actualItems);
        });
    }

    @Then("List {element} consists of items, where {string} has attribute {dataGenerator} from list variable {listVariable}")
    public void checkListItemsAttributeStrict(HtmlElement listElement, String listItemName, String attribute, Collection<?> expectedItemsRaw) {
        List<String> expectedItems = expectedItemsRaw.stream()
                .map(Object::toString)
                .collect(Collectors.toList());

        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            List<String> actualItems = listItems.stream()
                    .map(li -> HtmlElementHelper.findElementByName(li, listItemName))
                    .filter(HtmlElement::isDisplayed)
                    .map(target -> target.getAttribute(attribute))
                    .collect(Collectors.toList());
            Assertions.assertIterableEquals(expectedItems, actualItems);
        });
    }

    @Then("Each item in list {element} has following elements")
    public void checkListItemsStructure(HtmlElement listElement, List<String> elementNames) {
        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
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
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
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
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            listItems.forEach(li -> {
                HtmlElement targetElement = HtmlElementHelper.findElementByName(li, listItemName);
                targetElement.triggerAction(ActionType.MOUSE_OVER);
                String actualValue = targetElement.getCssValue(propertyName);
                verifier.test(actualValue);
            });
        });
    }

    @Then("Each {string} in list {element} have an alternating CSS-property {string} from the following list")
    public void checkListItemsAlternatingCssProperty(String listItemName, HtmlElement listElement, String propertyName, List<String> expectedValuesRaw) {
        List<String> expectedValues = expectedValuesRaw.stream()
                .map(s -> DataGeneratorsHelper.processString(s, context))
                .collect(Collectors.toList());

        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            int elementNumber = 0;
            for (HtmlElement li : listItems) {
                if (elementNumber >= expectedValues.size())
                    elementNumber = 0;
                StringVerifier verifier = new StringVerifier(expectedValues.get(elementNumber));

                HtmlElement targetElement = HtmlElementHelper.findElementByName(li, listItemName);
                String actualValue = targetElement.getCssValue(propertyName);
                verifier.test(actualValue);
                elementNumber++;
            }
        });
    }

    @Then("Each {string} in list {element} has a width {numberComparisonMethod} {dataGenerator} pixels")
    public void checkListItemsWidth(String listItemName, HtmlElement listElement, NumberComparisonMethod method, String expectedWidthRaw) {
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

    @Then("Following elements from list {element} are aligned to {alignment}")
    public void checkListItemsAlignment(HtmlElement listElement, Alignment alignment, List<String> listItemNames) {
        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            listItems.forEach(li -> {
                int baseline = alignment.getBaseline(li);
                listItemNames.forEach(listItemName -> {
                    HtmlElement targetElement = HtmlElementHelper.findElementByName(li, listItemName);
                    int actual = alignment.getBaseline(targetElement);
                    Assertions.assertEquals(baseline, actual);
                });
            });
        });
    }

    @Given("A user clicks on {string} with text {dataGenerator} in list {element}")
    public void clickListItem(String listItemElementName, String itemContent, HtmlElement listElement) {
        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            List<String> actualResult = new LinkedList<>();
            for (HtmlElement listItem : listItems) {
                HtmlElement innerElement = listItem.getChildElementByName(listItemElementName);
                if (innerElement == null)
                    continue;
                String actualText = (String) innerElement.triggerAction(ActionType.GET_TEXT);
                // todo unsupported [brackets] expressions
                if (itemContent.equals(actualText)) {
                    innerElement.triggerAction(ActionType.CLICK);
                    return;
                }
                actualResult.add(actualText);
            }
            Assertions.fail("List does not contain item with text " + itemContent + ". Actual items:\n" + actualResult);
        });
    }
}
