package ru.sooslick.qa.steps.browser;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.interactions.Actions;
import ru.sooslick.qa.core.NumberComparisonMethod;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.assertions.StringVerifier;
import ru.sooslick.qa.core.helper.HtmlElementHelper;
import ru.sooslick.qa.core.helper.WebDriverHelper;
import ru.sooslick.qa.core.repeaters.Repeat;
import ru.sooslick.qa.pagemodel.actions.ActionType;
import ru.sooslick.qa.pagemodel.annotations.Context;
import ru.sooslick.qa.pagemodel.element.HtmlElement;
import ru.sooslick.qa.pagemodel.element.ImageElement;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ElementSteps {

    @Context
    private ScenarioContext context;

    // todo actions usage is kinda random. Probably I should implement all steps as actions-based
    //  but only after resolving existing actions problems

    public static void checkAllElementsVisible(List<HtmlElement> elements) {
        Repeat.forEachUntilSuccess(elements, (element) ->
                Assertions.assertTrue(element.isDisplayed(), "Element '" + element.getName() + "' is not visible"));
    }

    @Then("Element {element} is visible")
    public void checkElementVisible(HtmlElement element) {
        checkAllElementsVisible(Collections.singletonList(element));
    }

    @Then("All elements from the following list are visible")
    public void checkElementsVisible(List<String> elementNames) {
        List<HtmlElement> elements = elementNames.stream()
                .map(name -> HtmlElementHelper.findElementByName(context.getLoadedPage(), name))
                .collect(Collectors.toList());
        checkAllElementsVisible(elements);
    }

    @Given("A user scrolls the page to element {element}")
    public void scrollToElement(HtmlElement targetElement) {
        Repeat.untilSuccess(() -> targetElement.triggerAction(ActionType.SCROLL_TO_ELEMENT));
    }

    @Given("A user scrolls the page to the top of the page")
    public void scrollToTop() {
        // todo probably I should implement page-context customizable actions
        //  so I can be sure I can rewrite this step as I want without modifying core steps
        //  and maybe implement non-js variant
        WebDriverHelper.runJs(context.getWebDriver(), "window.scrollTo(0, 0);");
    }

    @Then("Element {element} has a text {dataGenerator}")
    public void checkElementText(HtmlElement targetElement, String expectedText) {
        StringVerifier verifier = new StringVerifier(expectedText);
        Repeat.untilSuccess(() -> {
            String actualValue = targetElement.getText();
            verifier.test(actualValue);
        });
    }

    @Then("Element {element} contains any text")
    public void checkElementTextNotEmpty(HtmlElement targetElement) {
        Repeat.untilSuccess(() -> {
            String actualValue = targetElement.getText();
            Assertions.assertFalse(actualValue.isBlank());
        });
    }

    @Then("Element {element} has a CSS-property {string} with value {dataGenerator}")
    public void checkElementCssProperty(HtmlElement targetElement, String propertyName, String propertyValue) {
        StringVerifier verifier = new StringVerifier(propertyValue);
        Repeat.untilSuccess(() -> {
            String actualValue = targetElement.getCssValue(propertyName);
            verifier.test(actualValue);
        });
    }

    @Then("Element {element} has a CSS-property {string} with value {dataGenerator} when hovered")
    public void checkElementHoveredCssProperty(HtmlElement targetElement, String propertyName, String propertyValue) {
        StringVerifier verifier = new StringVerifier(propertyValue);
        Repeat.untilSuccess(() -> {
            targetElement.triggerAction(ActionType.MOUSE_OVER);
            String actualValue = targetElement.getCssValue(propertyName);
            verifier.test(actualValue);
        });
    }

    @Then("Element {element} has a CSS-property {string} with value {dataGenerator} when clicked")
    public void checkElementActiveCssProperty(HtmlElement targetElement, String propertyName, String propertyValue) {
        StringVerifier verifier = new StringVerifier(propertyValue);
        Repeat.untilSuccess(() -> {
            targetElement.triggerAction(ActionType.CLICK_AND_HOLD);
            String actualValue = targetElement.getCssValue(propertyName);
            new Actions(targetElement.getWrappedDriver())
                    .release()
                    .build()
                    .perform();
            verifier.test(actualValue);
        });
    }

    @Then("Element {element} has a height {numberComparisonMethod} {dataGenerator} pixels")
    public void checkElementHeight(HtmlElement targetElement, NumberComparisonMethod method, String height) {
        int expectedHeight = Integer.parseInt(height);
        Repeat.untilSuccess(() -> {
            int actualHeight = targetElement.getSize().getHeight();
            method.test(expectedHeight, actualHeight);
        });
    }

    @Then("Element {element} has a width {numberComparisonMethod} {dataGenerator} pixels")
    public void checkElementWidth(HtmlElement targetElement, NumberComparisonMethod method, String width) {
        int expectedWidth = Integer.parseInt(width);
        Repeat.untilSuccess(() -> {
            int actualWidth = targetElement.getSize().getWidth();
            method.test(expectedWidth, actualWidth);
        });
    }

    @Given("A user remembers the Y coordinate of element {element} as variable {string}")
    public void saveElementY(HtmlElement element, String variableName) {
        Repeat.untilSuccess(() -> context.saveVariable(variableName, element.getLocation().getY()));
    }

    @Then("Element {element} has Y coordinate {numberComparisonMethod} {dataGenerator} pixels")
    public void checkElementY(HtmlElement targetElement, NumberComparisonMethod method, String y) {
        int expectedY = Integer.parseInt(y);
        Repeat.untilSuccess(() -> {
            int actualY = targetElement.getLocation().getY();
            method.test(expectedY, actualY);
        });
    }

    @Then("Image {image} has a valid source")
    public void checkImageSource(ImageElement targetElement) {
        Repeat.untilSuccess(() -> Assertions.assertTrue(targetElement.isValid()));
    }

    @Given("A user hovers the cursor over the element {element}")
    public void mouseOverElement(HtmlElement targetElement) {
        Repeat.untilSuccess(() -> targetElement.triggerAction(ActionType.MOUSE_OVER));
    }

    @Then("Element {element} has an attribute {string} with value {dataGenerator}")
    public void checkElementAttribute(HtmlElement targetElement, String attribute, String expectedValue) {
        StringVerifier verifier = new StringVerifier(expectedValue);
        Repeat.untilSuccess(() -> {
            String actualValue = targetElement.getAttribute(attribute);
            verifier.test(actualValue);
        });
    }

    @Then("Checkbox {element} is {checkboxState}")
    public void isCheckboxChecked(HtmlElement targetElement, boolean expectedChecked) {
        Repeat.untilSuccess(() -> Assertions.assertEquals(
                expectedChecked,
                targetElement.triggerAction(ActionType.GET_CHECKBOX_STATE)
        ));
    }

    @Given("A user clicks on the element {element}")
    public void clickElement(HtmlElement targetElement) {
        Repeat.untilSuccess(targetElement::click);
    }
}
