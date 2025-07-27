package ru.sooslick.qa.steps.browser;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import ru.sooslick.qa.core.Alignment;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.assertions.StringVerifier;
import ru.sooslick.qa.core.helper.DataGeneratorsHelper;
import ru.sooslick.qa.core.helper.HtmlElementHelper;
import ru.sooslick.qa.core.helper.ItemListHelper;
import ru.sooslick.qa.core.repeaters.Repeat;
import ru.sooslick.qa.pagemodel.annotations.Context;
import ru.sooslick.qa.pagemodel.components.Component;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemListSteps {

    @Context
    private ScenarioContext context;

    @Then("List {element} has an item, where {string} has text {stringVerifier}")
    public void checkListItemPresence(HtmlElement listElement, String listItemName, StringVerifier expectedText) {
        ItemListHelper.validateListItem(listElement, listItemName);
        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            List<String> actualItems = listItems.stream()
                    .map(li -> HtmlElementHelper.findElementByName(li, listItemName))
                    .filter(HtmlElement::isDisplayed)
                    .map(HtmlElement::getText)
                    .collect(Collectors.toList());
            expectedText.testAny(actualItems);
        });
    }

    @Then("List {element} has no items, where {string} has text {dataGenerator}")
    public void checkListItemNotPresented(HtmlElement listElement, String listItemName, String expectedText) {
        ItemListHelper.validateListItem(listElement, listItemName);
        StringVerifier expected = new StringVerifier(expectedText).not();
        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            List<String> actualItems = listItems.stream()
                    .map(li -> HtmlElementHelper.findElementByName(li, listItemName))
                    .filter(HtmlElement::isDisplayed)
                    .map(HtmlElement::getText)
                    .collect(Collectors.toList());
            expected.testAll(actualItems);
        });
    }

    @Then("List {element} consists of items, where {string} has text")
    public void checkListItemsStrict(HtmlElement listElement, String listItemName, List<String> expectedItemsRaw) {
        ItemListHelper.validateListItem(listElement, listItemName);
        List<StringVerifier> expectedItems = expectedItemsRaw.stream()
                .map(s -> DataGeneratorsHelper.processString(s, context))
                .map(StringVerifier::new)
                .collect(Collectors.toList());

        Repeat.untilSuccess(() -> {
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
        ItemListHelper.validateListItem(listElement, listItemName);
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

    @Then("Item with number {intGenerator} in list {element} has element {string} visible")
    public void checkListItemElementVisibility(int number, HtmlElement listElement, String listItemName) {
        ItemListHelper.validateListItem(listElement, listItemName);
        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            Assertions.assertTrue(number <= listItems.size(), listElement.getName() + " has no item with number " + number + ", list has " + listItems.size() + " total");
            HtmlElement targetLi = listItems.get(number - 1);
            HtmlElement targetElement = HtmlElementHelper.findElementByName(targetLi, listItemName);
            Assertions.assertTrue(targetElement.isDisplayed());
        });
    }

    @Then("Item with number {intGenerator} in list {element} has element {string} not visible")
    public void checkListItemElementAbsence(int number, HtmlElement listElement, String listItemName) {
        ItemListHelper.validateListItem(listElement, listItemName);
        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            Assertions.assertTrue(number <= listItems.size(), listElement.getName() + " has no item with number " + number + ", list has " + listItems.size() + " total");
            HtmlElement targetLi = listItems.get(number - 1);
            HtmlElement targetElement = HtmlElementHelper.findElementByName(targetLi, listItemName);
            Assertions.assertFalse(targetElement.isDisplayed());
        });
    }

    @Then("{string} with number {intGenerator} in list {element} has a CSS-property {string} with value {stringVerifier}")
    public void checkListItemCssProperty(String listItemName, int number, HtmlElement listElement, String propertyName, StringVerifier expectedValue) {
        ItemListHelper.validateListItem(listElement, listItemName);
        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            Assertions.assertTrue(number <= listItems.size(), listElement.getName() + " has no item with number " + number + ", list has " + listItems.size() + " total");
            HtmlElement targetLi = listItems.get(number - 1);
            HtmlElement targetElement = HtmlElementHelper.findElementByName(targetLi, listItemName);
            String actualValue = targetElement.getCssValue(propertyName);
            expectedValue.test(actualValue);
        });
    }

    @Then("{string} with number {intGenerator} in list {element} has an attribute {string} with value {stringVerifier}")
    public void checkListItemAttribute(String listItemName, int number, HtmlElement listElement, String attrName, StringVerifier expectedValue) {
        ItemListHelper.validateListItem(listElement, listItemName);
        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            Assertions.assertTrue(number <= listItems.size(), listElement.getName() + " has no item with number " + number + ", list has " + listItems.size() + " total");
            HtmlElement targetLi = listItems.get(number - 1);
            HtmlElement targetElement = HtmlElementHelper.findElementByName(targetLi, listItemName);
            String actualValue = targetElement.getAttribute(attrName);
            expectedValue.test(actualValue);
        });
    }

    @Then("{string} with number {intGenerator} in list {element} has no attribute {string}")
    public void checkListItemAttributeAbsence(String listItemName, int number, HtmlElement listElement, String attrName) {
        ItemListHelper.validateListItem(listElement, listItemName);
        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            Assertions.assertTrue(number <= listItems.size(), listElement.getName() + " has no item with number " + number + ", list has " + listItems.size() + " total");
            HtmlElement targetLi = listItems.get(number - 1);
            HtmlElement targetElement = HtmlElementHelper.findElementByName(targetLi, listItemName);
            Assertions.assertNull(targetElement.getAttribute(attrName));
        });
    }

    @Then("Following elements from list {element} are aligned to {alignment}")
    public void checkListItemsAlignment(HtmlElement listElement, Alignment alignment, List<String> listItemNames) {
        ItemListHelper.validateListItems(listElement, listItemNames);
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

    @Given("A user clicks on {string} with text {stringVerifier} in list {element}")
    public void clickListItem(String listItemName, StringVerifier expectedContent, HtmlElement listElement) {
        ItemListHelper.validateListItem(listElement, listItemName);
        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            List<String> actualResult = new LinkedList<>();
            for (HtmlElement listItem : listItems) {
                HtmlElement innerElement = HtmlElementHelper.findElementByName(listItem, listItemName);
                String actualText = innerElement.getText();
                if (expectedContent.get(actualText)) {
                    innerElement.click();
                    return;
                }
                actualResult.add(actualText);
            }
            Assertions.fail("List does not contain item with text " + expectedContent + ". Actual items:\n" + actualResult);
        });
    }

    @Given("A user clicks on {string} with number {intGenerator} in list {element}")
    public void clickListItem(String listItemName, int orderNumber, HtmlElement listElement) {
        ItemListHelper.validateListItem(listElement, listItemName);
        if (orderNumber < 1)
            throw new InvalidArgumentException("Number must be positive");
        int index = orderNumber - 1;

        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            if (index >= listItems.size())
                throw new AssertionError("Item list does not have enough items, total items " + listItems.size() + ", expecting " + orderNumber);
            HtmlElement target = listItems.get(index);
            HtmlElementHelper.findElementByName(target, listItemName).click();
        });
    }

    @Then("List {element} has items")
    public void checkListHasItems(HtmlElement listElement) {
        Repeat.untilSuccess(() -> {
            By locator = listElement.getComponentLocator(Component.LIST_ITEM);
            Assertions.assertFalse(listElement.findElements(locator).isEmpty(),
                    listElement.getName() + " has no items");
        });
    }

    @Then("List {element} has {int} item")
    @Then("List {element} has {int} items")
    public void checkListHasItems(HtmlElement listElement, int amount) {
        Repeat.untilSuccess(() -> {
            By locator = listElement.getComponentLocator(Component.LIST_ITEM);
            Assertions.assertEquals(amount, listElement.findElements(locator).size());
        });
    }
}
