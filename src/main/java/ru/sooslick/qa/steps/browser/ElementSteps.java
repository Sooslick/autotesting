package ru.sooslick.qa.steps.browser;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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
            String actualValue = (String) element.triggerAction(ActionType.GET_TEXT);
            verifier.test(actualValue);
        });
    }

    public static void checkCssProperty(HtmlElement targetElement, String propertyName, String propertyValue) {
        StringVerifier verifier = new StringVerifier(propertyValue);
        Repeat.untilSuccess(targetElement, (element) -> {
            // todo can i get rid of cast?
            //  upd can't unless i replace enum ActionType with generic of some kind
            String actualValue = (String) element.triggerAction(ActionType.GET_CSS_PROPERTY, propertyName);
            verifier.test(actualValue);
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

    @Then("Element {string} has a text {string}")
    public void checkElementText(String elementName, String expectedValue) {
        HtmlElement element = HtmlElementHelper.findElementByName(context.getLoadedPage(), elementName);
        checkElementText(element, expectedValue);
    }

    @Then("Element {string} has a css-property {string} with value {string}")
    public void checkElementCssProperty(String elementName, String propertyName, String propertyValue) {
        HtmlElement element = HtmlElementHelper.findElementByName(context.getLoadedPage(), elementName);
        checkCssProperty(element, propertyName, propertyValue);
    }

    // todo move scroll steps to its own class and implement following steps:
    //  - scroll to top / bottom (left / right)
    //  - scroll N px up / down (left / right)
    //  - bonus: scroll inner element
}
