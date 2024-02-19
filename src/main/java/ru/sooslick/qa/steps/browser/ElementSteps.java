package ru.sooslick.qa.steps.browser;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import ru.sooslick.qa.core.NumberComparisonMethod;
import ru.sooslick.qa.core.Repeat;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.assertions.StringVerifier;
import ru.sooslick.qa.core.helper.HtmlElementHelper;
import ru.sooslick.qa.pagemodel.ActionType;
import ru.sooslick.qa.pagemodel.HtmlElement;
import ru.sooslick.qa.pagemodel.annotations.Context;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ElementSteps {

    @Context
    private ScenarioContext context;

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
        Repeat.untilSuccess(targetElement, (element) ->
                element.triggerAction(ActionType.SCROLL_TO_ELEMENT));
    }

    @Given("A user scrolls the page to the top of the page")
    public void scrollToTop() {
        // todo probably I should implement page-context customizable actions
        //  so I can be sure I can rewrite this step as I want without modifying core steps
        //  and maybe implement non-js variant
        WebDriver driver = context.getWebDriver();
        if (driver instanceof JavascriptExecutor jsExecutor) {
            jsExecutor.executeScript("window.scrollTo(0, 0);");
        } else
            throw new UnsupportedOperationException("Can't scroll to the top of the page");
    }

    @Then("Element {element} has a text {dataGenerator}")
    public void checkElementText(HtmlElement targetElement, String expectedText) {
        StringVerifier verifier = new StringVerifier(expectedText);
        Repeat.untilSuccess(targetElement, element -> {
            // todo research can I get rid of this cast?
            String actualValue = (String) element.triggerAction(ActionType.GET_TEXT);
            verifier.test(actualValue);
        });
    }

    @Then("Element {element} has a CSS-property {string} with value {dataGenerator}")
    public void checkElementCssProperty(HtmlElement targetElement, String propertyName, String propertyValue) {
        StringVerifier verifier = new StringVerifier(propertyValue);
        Repeat.untilSuccess(targetElement, (element) -> {
            String actualValue = element.getCssValue(propertyName);
            verifier.test(actualValue);
        });
    }

    @Then("Element {element} has a height {numberComparisonMethod} {dataGenerator} pixels")
    public void checkElementHeight(HtmlElement targetElement, NumberComparisonMethod method, String height) {
        int expectedHeight = Integer.parseInt(height);
        Repeat.untilSuccess(targetElement, (element) -> {
            int actualHeight = element.getSize().getHeight();
            method.test(expectedHeight, actualHeight);
        });
    }

    @Given("A user remembers the Y coordinate of element {element} as variable {string}")
    public void saveElementY(HtmlElement element, String variableName) {
        Repeat.untilSuccess(element, e ->
                context.saveVariable(variableName, e.getLocation().getY()));
    }

    @Then("Element {element} has Y coordinate {numberComparisonMethod} {dataGenerator} pixels")
    public void checkElementY(HtmlElement targetElement, NumberComparisonMethod method, String y) {
        int expectedY = Integer.parseInt(y);
        Repeat.untilSuccess(targetElement, (element) -> {
            int actualY = element.getLocation().getY();
            method.test(expectedY, actualY);
        });
    }

    // todo move scroll steps to its own class and implement following steps:
    //  - scroll to bottom (left / right)
    //  - scroll N px up / down (left / right)
    //  - bonus: scroll inner element

    // todo element width + width x height steps
}
