package ru.sooslick.qa.steps.browser;

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

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ItemListForEachSteps {

    @Context
    private ScenarioContext context;

    public static void testListItems(HtmlElement listElement, Consumer<HtmlElement> steps) {
        Repeat.untilSuccess(() -> ItemListHelper.getListItems(listElement).forEach(steps));
    }

    public static void testListItems(HtmlElement listElement, String listItemElementName, Consumer<HtmlElement> steps) {
        ItemListHelper.validateListItem(listElement, listItemElementName);
        testListItems(listElement, li -> {
            HtmlElement targetElement = HtmlElementHelper.findElementByName(li, listItemElementName);
            steps.accept(targetElement);
        });
    }

    @Then("Each item in list {element} has following elements")
    public void checkListItemsStructure(HtmlElement listElement, List<String> elementNames) {
        ItemListHelper.validateListItems(listElement, elementNames);
        testListItems(listElement, li -> {
            // TODO: group assertion? instead of foreach. Same for each "each" steps
            elementNames.forEach(name -> {
                HtmlElement targetElement = HtmlElementHelper.findElementByName(li, name);
                Assertions.assertTrue(targetElement.isDisplayed(), "Element '" + targetElement.getName() + "' is not visible");
            });
        });
    }

    @Then("Each {string} in list {element} has no attribute {string}")
    public void checkListItemsAttribute(String listItemName, HtmlElement listElement, String attrName) {
        testListItems(listElement, listItemName, targetElement ->
                Assertions.assertNull(targetElement.getAttribute(attrName)));
    }

    @Then("Each {string} in list {element} has a text {stringVerifier}")
    public void checkListItemsText(String listItemName, HtmlElement listElement, StringVerifier expectedValue) {
        testListItems(listElement, listItemName, targetElement ->
                expectedValue.test(targetElement.getText()));
    }

    @Then("Each {string} in list {element} has a CSS-property {string} with value {stringVerifier}")
    public void checkListItemsCssProperty(String listItemName, HtmlElement listElement, String propertyName, StringVerifier expectedValue) {
        testListItems(listElement, listItemName, targetElement ->
                expectedValue.test(targetElement.getCssValue(propertyName)));
    }

    @Then("Each {string} in list {element} has a CSS-property {string} with value {stringVerifier} when hovered")
    public void checkListItemsHoveredCssProperty(String listItemName, HtmlElement listElement, String propertyName, StringVerifier expectedValue) {
        testListItems(listElement, listItemName, targetElement -> {
            targetElement.triggerAction(ActionType.MOUSE_OVER);
            expectedValue.test(targetElement.getCssValue(propertyName));
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
        int expectedWidth = Integer.parseInt(expectedWidthRaw);
        testListItems(listElement, listItemName, targetElement ->
                method.test(expectedWidth, targetElement.getSize().getWidth()));
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
}
