package ru.sooslick.qa.steps.browser;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
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

public class ElementSteps {

    @Context
    private ScenarioContext context;

    // todo actions usage is kinda random. Probably I should implement all steps as actions-based
    //  but only after resolving existing actions problems

    // todo Invisible Step
    public static void checkAllElementsVisible(List<HtmlElement> elements) {
        // todo NoSuchElementException supersedes assertion failure (UPD 2025.02.26: confirm fix)
        //  flawed Actions system
        Repeat.forEachUntilSuccess(elements, (element) ->
                Assertions.assertTrue(element.isDisplayed(), "Element '" + element.getName() + "' is not visible"));
    }

    // todo Invisible Step
    public static void checkAllElementsNotVisible(List<HtmlElement> elements) {
        Repeat.forEachUntilSuccess(elements, (element) -> {
            try {
                Assertions.assertFalse(element.isDisplayed(), "Element '" + element.getName() + "' is visible, expected not");
            } catch (NoSuchElementException ignored) {
            } // Element does not present in DOM - so it is not visible
        });
    }

    @Then("Element {element} is visible")
    public void checkElementVisible(HtmlElement element) {
        checkAllElementsVisible(Collections.singletonList(element));
    }

    @Then("Element {element} is not visible")
    public void checkElementNotVisible(HtmlElement element) {
        checkAllElementsNotVisible(Collections.singletonList(element));
    }

    @Then("All elements from the following list are visible")
    public void checkElementsVisible(List<String> elementNames) {
        List<HtmlElement> elements = HtmlElementHelper.findElementsByNames(context.getLoadedPage(), elementNames);
        checkAllElementsVisible(elements);
    }

    @Then("All elements from the following list are not visible")
    public void checkElementsNotVisible(List<String> elementNames) {
        List<HtmlElement> elements = HtmlElementHelper.findElementsByNames(context.getLoadedPage(), elementNames);
        checkAllElementsNotVisible(elements);
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

    @Then("Page vertical scroll position is {numberComparisonMethod} {dataGenerator} pixels")
    public void checkVerticalScroll(NumberComparisonMethod numberComparisonMethod, String expectedPosRaw) {
        int expectedPos = Integer.parseInt(expectedPosRaw);
        Repeat.untilSuccess(() -> {
            long actualPos = (long) ((JavascriptExecutor) context.getWebDriver()).executeScript("return window.pageYOffset");
            numberComparisonMethod.test(expectedPos, actualPos);
        });
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

    @Given("A user moves mouse to point {int}, {int}")
    public void mouseMoveTo(int x, int y) {
        Repeat.untilSuccess(() -> new Actions(context.getWebDriver())
                .moveToLocation(x, y)
                .build()
                .perform());
    }

    @Then("Element {element} has an attribute {string} with value {dataGenerator}")
    public void checkElementAttribute(HtmlElement targetElement, String attribute, String expectedValue) {
        StringVerifier verifier = new StringVerifier(expectedValue);
        Repeat.untilSuccess(() -> {
            String actualValue = targetElement.getAttribute(attribute);
            verifier.test(actualValue);
        });
    }

    @Then("Element {element} has an attribute {string} which is not {dataGenerator}")
    public void checkElementAttributeNot(HtmlElement targetElement, String attribute, String expectedValue) {
        StringVerifier verifier = new StringVerifier(expectedValue).not();
        Repeat.untilSuccess(() -> {
            String actualValue = targetElement.getAttribute(attribute);
            verifier.test(actualValue);
        });
    }

    @Then("Element {element} has no attribute {string}")
    public void checkElementAttributeNotPresented(HtmlElement targetElement, String attribute) {
        Repeat.untilSuccess(() -> Assertions.assertNull(targetElement.getAttribute(attribute)));
    }

    @Then("A user remembers the text in element {element} to variable {string}")
    public void saveElementText(HtmlElement targetElement, String variableName) {
        Repeat.untilSuccess(() -> {
            String actualValue = targetElement.getText();
            context.saveVariable(variableName, actualValue);
        });
    }

    @Then("A user remembers the attribute {string} of element {element} to variable {string}")
    public void saveElementAttribute(String attribute, HtmlElement targetElement, String variableName) {
        Repeat.untilSuccess(() -> {
            String actualValue = targetElement.getAttribute(attribute);
            context.saveVariable(variableName, actualValue);
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

    @Given("A user types {dataGenerator} to {element}")
    public void sendKeys(String value, HtmlElement target) {
        target.sendKeys(value);
    }

    @Given("A user clears element {element}")
    public void clearElement(HtmlElement target) {
        target.clear();
    }

    @Given("A user press {string} {int} times")
    public void pressButton(String buttonName, int amount) {
        Keys button = Keys.valueOf(buttonName.toUpperCase());
        Actions actions = new Actions(context.getWebDriver());
        for (int i = 0; i < amount; i++) {
            actions.sendKeys(button);
        }
        actions.build().perform();
    }

    @Given("A user pastes from clipboard to {element}")
    public void pasteFromClipboard(HtmlElement target) {
        clickElement(target);
        new Actions(context.getWebDriver())
                .keyDown(Keys.CONTROL)
                .sendKeys("v")
                .keyUp(Keys.CONTROL)
                .build()
                .perform();
    }
}
