package ru.sooslick.qa.steps.browser;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.assertions.StringVerifier;
import ru.sooslick.qa.core.helper.HtmlElementHelper;
import ru.sooslick.qa.pagemodel.ActionType;
import ru.sooslick.qa.pagemodel.HtmlElement;
import ru.sooslick.qa.steps.RepeatSteps;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ElementSteps {
    private ScenarioContext context;

    public static void checkAllElementsVisible(Collection<HtmlElement> elementCollection) {
        RepeatSteps.forEachUntilSuccess(elementCollection, (element) ->
                element.triggerAction(ActionType.CHECK_ELEMENT_VISIBLE));
    }

    public static void scrollToElement(HtmlElement targetElement) {
        RepeatSteps.untilSuccess(targetElement, (element) ->
                element.triggerAction(ActionType.SCROLL_TO_ELEMENT));
    }

    public static void checkCssProperty(HtmlElement targetElement, String propertyName, String propertyValue) {
        StringVerifier verifier = new StringVerifier(propertyValue);
        RepeatSteps.untilSuccess(targetElement, (element) -> {
            // todo can i get rid of cast?
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

    @Given("A user scrolls the page to element {string}")
    public void scrollToElement(String elementName) {
        HtmlElement element = HtmlElementHelper.findElementByName(context.getLoadedPage(), elementName);
        scrollToElement(element);
    }

    @Given("A user scrolls the page to the top of the page")
    public void scrollToTop() {
        WebDriver driver = context.getWebDriver();
        if (driver instanceof JavascriptExecutor jsExecutor) {
            // todo repeater?
            jsExecutor.executeScript("window.scrollTo(0, 0);");
        } else
            // todo impl?
            throw new UnsupportedOperationException("Unimplemented scroll to the top action");
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
