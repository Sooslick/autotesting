package ru.sooslick.qa.steps.browser;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.assertions.CollectionVerifier;
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
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ItemListSteps {

    @Context
    private ScenarioContext context;

    public static <T> void testListItems(HtmlElement listElement, String listItemName, Function<HtmlElement, T> listMapper, Consumer<Collection<T>> assertions) {
        ItemListHelper.validateListItem(listElement, listItemName);
        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            List<T> actualItems = listItems.stream()
                    .map(li -> HtmlElementHelper.findElementByName(li, listItemName))
                    .filter(HtmlElement::isDisplayed)
                    .map(listMapper)
                    .collect(Collectors.toList());
            assertions.accept(actualItems);
        });
    }

    public static void testListItem(HtmlElement listElement, String listItemName, int number, Consumer<HtmlElement> steps) {
        if (number < 1)
            throw new InvalidArgumentException("Number must be positive");
        int index = number - 1;
        ItemListHelper.validateListItem(listElement, listItemName);
        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            Assertions.assertTrue(index < listItems.size(), listElement.getName() + " has no item with number " + number + ", list has " + listItems.size() + " total");
            HtmlElement targetLi = listItems.get(index);
            HtmlElement targetElement = HtmlElementHelper.findElementByName(targetLi, listItemName);
            steps.accept(targetElement);
        });
    }

    public static void testListItem(HtmlElement listElement, String listItemName, StringVerifier expectedContent, Consumer<HtmlElement> steps) {
        ItemListHelper.validateListItem(listElement, listItemName);
        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            List<String> actualResult = new LinkedList<>();
            for (HtmlElement listItem : listItems) {
                HtmlElement innerElement = HtmlElementHelper.findElementByName(listItem, listItemName);
                String actualText = innerElement.getText();
                if (expectedContent.get(actualText)) {
                    steps.accept(innerElement);
                    return;
                }
                actualResult.add(actualText);
            }
            Assertions.fail("List does not contain item with text " + expectedContent + ". Actual items:\n" + actualResult);
        });
    }

    @Then("List {element} has an item, where {string} has text {stringVerifier}")
    public void checkListItemPresence(HtmlElement listElement, String listItemName, StringVerifier expectedText) {
        testListItems(listElement, listItemName, HtmlElement::getText, expectedText::testAny);
    }

    @Then("List {element} has no items, where {string} has text {stringVerifier}")
    public void checkListItemNotPresented(HtmlElement listElement, String listItemName, StringVerifier expectedText) {
        expectedText.not();
        testListItems(listElement, listItemName, HtmlElement::getText, expectedText::testAll);
    }

    @Then("List {element} consists of items, where {string} has text")
    public void checkListItemsStrict(HtmlElement listElement, String listItemName, List<String> expectedItemsRaw) {
        CollectionVerifier<String> expectedCollection = new CollectionVerifier<>(DataGeneratorsHelper.processList(expectedItemsRaw, context))
                .compareFunction((e, a) -> new StringVerifier(e).get(a));
        testListItems(listElement, listItemName, HtmlElement::getText, expectedCollection::testStrict);
    }

    @Then("List {element} consists of items, where {string} has attribute {string} from list variable {listVariable}")
    public void checkListItemsAttributeStrict(HtmlElement listElement, String listItemName, String attribute, Collection<?> expectedItemsRaw) {
        List<String> expectedAttrs = expectedItemsRaw.stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        CollectionVerifier<String> expected = new CollectionVerifier<>(expectedAttrs);
        testListItems(listElement, listItemName, e -> e.getAttribute(attribute), expected::testStrict);
    }

    @Then("Item with number {intGenerator} in list {element} has element {string} visible")
    public void checkListItemElementVisibility(int number, HtmlElement listElement, String listItemName) {
        testListItem(listElement, listItemName, number, (listItemElement) ->
                Assertions.assertTrue(listItemElement.isDisplayed()));
    }

    @Then("Item with number {intGenerator} in list {element} has element {string} not visible")
    public void checkListItemElementAbsence(int number, HtmlElement listElement, String listItemName) {
        testListItem(listElement, listItemName, number, (listItemElement) ->
                Assertions.assertFalse(listItemElement.isDisplayed()));
    }

    @Then("{string} with number {intGenerator} in list {element} has a CSS-property {string} with value {stringVerifier}")
    public void checkListItemCssProperty(String listItemName, int number, HtmlElement listElement, String propertyName, StringVerifier expectedValue) {
        testListItem(listElement, listItemName, number, (listItemElement) ->
                expectedValue.test(listItemElement.getCssValue(propertyName)));
    }

    @Then("{string} with number {intGenerator} in list {element} has an attribute {string} with value {stringVerifier}")
    public void checkListItemAttribute(String listItemName, int number, HtmlElement listElement, String attrName, StringVerifier expectedValue) {
        testListItem(listElement, listItemName, number, (listItemElement) ->
                expectedValue.test(listItemElement.getAttribute(attrName)));
    }

    @Then("{string} with number {intGenerator} in list {element} has no attribute {string}")
    public void checkListItemAttributeAbsence(String listItemName, int number, HtmlElement listElement, String attrName) {
        testListItem(listElement, listItemName, number, (listItemElement) ->
                Assertions.assertNull(listItemElement.getAttribute(attrName)));
    }

    @Then("{string} with text {stringVerifier} in list {element} has following CSS-properties")
    public void checkListItemCssProperty(String listItemName, StringVerifier matchText, HtmlElement listElement, Map<String, String> cssProperties) {
        testListItem(listElement, listItemName, matchText, (listItemElement) ->
                Assertions.assertAll(
                        cssProperties.entrySet().stream()
                                .map(kv -> () -> {
                                    StringVerifier cssPropertyVerifier = new StringVerifier(kv.getValue());
                                    cssPropertyVerifier.test(listItemElement.getCssValue(kv.getKey()));
                                })
                ));
    }

    @Given("A user clicks on {string} with text {stringVerifier} in list {element}")
    public void clickListItem(String listItemName, StringVerifier expectedContent, HtmlElement listElement) {
        testListItem(listElement, listItemName, expectedContent, HtmlElement::click);
    }

    @Given("A user clicks on {string} with number {intGenerator} in list {element}")
    public void clickListItem(String listItemName, int orderNumber, HtmlElement listElement) {
        testListItem(listElement, listItemName, orderNumber, HtmlElement::click);
    }

    @Then("List {element} has items")
    public void checkListHasItems(HtmlElement listElement) {
        Repeat.untilSuccess(() -> {
            By locator = listElement.getComponent(Component.LIST_ITEM).locator();
            Assertions.assertFalse(listElement.findElements(locator).isEmpty(),
                    listElement.getName() + " has no items");
        });
    }

    @Then("List {element} has {int} item")
    @Then("List {element} has {int} items")
    public void checkListHasItems(HtmlElement listElement, int amount) {
        Repeat.untilSuccess(() -> {
            By locator = listElement.getComponent(Component.LIST_ITEM).locator();
            Assertions.assertEquals(amount, listElement.findElements(locator).size());
        });
    }
}
