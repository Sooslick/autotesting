package ru.sooslick.qa.steps.browser;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import ru.sooslick.qa.core.ScenarioContext;
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

    // todo move scroll steps to its own class and implement following steps:
    //  - scroll to top / bottom (left / right)
    //  - scroll N px up / down (left / right)
    //  - bonus: scroll inner element
}
