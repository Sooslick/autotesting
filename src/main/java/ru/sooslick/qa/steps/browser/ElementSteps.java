package ru.sooslick.qa.steps.browser;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
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

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ElementSteps {
    private ScenarioContext context;

    // todo most of static methods duplicates cucumber-steps
    //  prob should introduce custom cucumber type

    // todo: separate step definitions, parameter types and step impls
    public static void checkAllElementsVisible(Collection<HtmlElement> elementCollection) {
        Repeat.forEachUntilSuccess(elementCollection, (element) ->
                element.triggerAction(ActionType.CHECK_ELEMENT_VISIBLE));
    }

    public static void scrollToElement(HtmlElement targetElement) {
        Repeat.untilSuccess(targetElement, (element) ->
                element.triggerAction(ActionType.SCROLL_TO_ELEMENT));
    }

    public static void checkElementText(HtmlElement targetElement, String expectedText) {
        StringVerifier verifier = new StringVerifier(expectedText);
        Repeat.untilSuccess(targetElement, element -> {
            // todo research can I get rid of this cast?
            String actualValue = (String) element.triggerAction(ActionType.GET_TEXT);
            verifier.test(actualValue);
        });
    }

    public static void checkCssProperty(HtmlElement targetElement, String propertyName, String propertyValue) {
        StringVerifier verifier = new StringVerifier(propertyValue);
        Repeat.untilSuccess(targetElement, (element) -> {
            String actualValue = element.getCssValue(propertyName);
            verifier.test(actualValue);
        });
    }

    public static void checkElementHeight(HtmlElement targetElement, NumberComparisonMethod method, int expectedHeight) {
        Repeat.untilSuccess(targetElement, (element) -> {
            int actualHeight = element.getSize().getHeight();
            method.test(expectedHeight, actualHeight);
        });
    }

    public static void checkElementY(HtmlElement targetElement, NumberComparisonMethod method, int expectedY) {
        Repeat.untilSuccess(targetElement, (element) -> {
            int actualY = element.getLocation().getY();
            method.test(expectedY, actualY);
        });
    }

    // todo probably I should abstractize context stuff
    @Before
    public void updateContext(Scenario scenario) {
        if (context == null)
            context = ScenarioContext.getContext(scenario);
    }

    @Then("Element {string} is visible")
    public void checkElementVisible(String elementName) {
        HtmlElement element = HtmlElementHelper.findElementByName(context.getLoadedPage(), elementName);
        checkAllElementsVisible(Collections.singleton(element));
    }

    @Then("All elements from the following list are visible")
    public void checkAllElementsVisible(List<String> elementNames) {
        List<HtmlElement> elementsToCheck = HtmlElementHelper.findElementsByNames(context.getLoadedPage(), elementNames);
        checkAllElementsVisible(elementsToCheck);
    }

    // todo not visible checks

    @Given("A user scrolls the page to element {string}")
    public void scrollToElement(String elementName) {
        HtmlElement element = HtmlElementHelper.findElementByName(context.getLoadedPage(), elementName);
        scrollToElement(element);
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

    @Then("Element {string} has a text {dataGenerator}")
    public void checkElementText(String elementName, String expectedValue) {
        HtmlElement element = HtmlElementHelper.findElementByName(context.getLoadedPage(), elementName);
        checkElementText(element, expectedValue);
    }

    @Then("Element {string} has a CSS-property {string} with value {dataGenerator}")
    public void checkElementCssProperty(String elementName, String propertyName, String propertyValue) {
        HtmlElement element = HtmlElementHelper.findElementByName(context.getLoadedPage(), elementName);
        checkCssProperty(element, propertyName, propertyValue);
    }

    @Then("Element {string} has a height {numberComparisonMethod} {dataGenerator} pixels")
    public void checkElementHeight(String elementName, NumberComparisonMethod method, String height) {
        HtmlElement element = HtmlElementHelper.findElementByName(context.getLoadedPage(), elementName);
        checkElementHeight(element, method, Integer.parseInt(height));
    }

    @Given("A user remembers the Y coordinate of element {string} as variable {string}")
    public void saveElementY(String elementName, String variableName) {
        HtmlElement element = HtmlElementHelper.findElementByName(context.getLoadedPage(), elementName);
        Repeat.untilSuccess(element, e ->
                context.saveVariable(variableName, e.getLocation().getY()));
    }

    @Then("Element {string} has Y coordinate {numberComparisonMethod} {dataGenerator} pixels")
    public void checkElementY(String elementName, NumberComparisonMethod method, String y) {
        HtmlElement element = HtmlElementHelper.findElementByName(context.getLoadedPage(), elementName);
        checkElementY(element, method, Integer.parseInt(y));
    }

    // todo move scroll steps to its own class and implement following steps:
    //  - scroll to bottom (left / right)
    //  - scroll N px up / down (left / right)
    //  - bonus: scroll inner element

    // todo move parameterTypes

    // todo element width + width x height steps
}
