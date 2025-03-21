package ru.sooslick.qa.steps.browser;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.InvalidArgumentException;
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
        List<StringVerifier> expectedItems = expectedItemsRaw.stream()
                .map(s -> DataGeneratorsHelper.processString(s, context))
                .map(StringVerifier::new)
                .collect(Collectors.toList());

        Repeat.untilSuccess(() -> {
            // todo find a way to check listElement is a valid element outside repeat
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            List<String> actualItems = listItems.stream()
                    .map(li -> HtmlElementHelper.findElementByName(li, listItemName))
                    .filter(HtmlElement::isDisplayed)
                    .map(HtmlElement::getText)
                    .collect(Collectors.toList());

            Assertions.assertEquals(expectedItems.size(), actualItems.size());
            for (int i = 0; i < expectedItems.size(); i++) {
                expectedItems.get(i).test(actualItems.get(i));
            }
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
                Assertions.assertTrue(targetElement.isDisplayed(), "Element '" + targetElement.getName() + "' is not visible");
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
    public void clickListItem(String listItemElementName, String expectedContent, HtmlElement listElement) {
        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            List<String> actualResult = new LinkedList<>();
            for (HtmlElement listItem : listItems) {
                HtmlElement innerElement = HtmlElementHelper.findElementByName(listItem, listItemElementName);
                String actualText = innerElement.getText();
                StringVerifier v = new StringVerifier(expectedContent);
                if (v.get(actualText)) {
                    innerElement.click();
                    return;
                }
                actualResult.add(actualText);
            }
            Assertions.fail("List does not contain item with text " + expectedContent + ". Actual items:\n" + actualResult);
        });
    }

    @Given("A user clicks on {string} with number {int} in list {element}")
    public void clickListItem(String listItemElementName, int orderNumber, HtmlElement listElement) {
        if (orderNumber < 1)
            throw new InvalidArgumentException("Number must be positive");
        int index = orderNumber - 1;

        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            if (index >= listItems.size())
                throw new AssertionError("Item list does not have enough items, total items " + listItems.size() + ", expecting " + orderNumber);
            HtmlElement target = listItems.get(index);
            HtmlElementHelper.findElementByName(target, listItemElementName).click();
        });
    }
}
