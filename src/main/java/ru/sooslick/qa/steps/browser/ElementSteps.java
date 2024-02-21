package ru.sooslick.qa.steps.browser;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import ru.sooslick.qa.core.NumberComparisonMethod;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.assertions.StringVerifier;
import ru.sooslick.qa.core.helper.HtmlElementHelper;
import ru.sooslick.qa.core.helper.WebDriverHelper;
import ru.sooslick.qa.core.repeaters.Repeat;
import ru.sooslick.qa.pagemodel.ActionType;
import ru.sooslick.qa.pagemodel.HtmlElement;
import ru.sooslick.qa.pagemodel.ImageElement;
import ru.sooslick.qa.pagemodel.annotations.Context;

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
                element.triggerAction(ActionType.CHECK_ELEMENT_VISIBLE));
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

    // todo not visible checks

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
            String actualValue = (String) targetElement.triggerAction(ActionType.GET_TEXT);
            verifier.test(actualValue);
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

    @Then("Element {element} has a height {numberComparisonMethod} {dataGenerator} pixels")
    public void checkElementHeight(HtmlElement targetElement, NumberComparisonMethod method, String height) {
        int expectedHeight = Integer.parseInt(height);
        Repeat.untilSuccess(() -> {
            int actualHeight = targetElement.getSize().getHeight();
            method.test(expectedHeight, actualHeight);
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

    // todo move scroll steps to its own class and implement following steps:
    //  - scroll to bottom (left / right)
    //  - scroll N px up / down (left / right)
    //  - bonus: scroll inner element

    // todo element width + width x height steps
}
