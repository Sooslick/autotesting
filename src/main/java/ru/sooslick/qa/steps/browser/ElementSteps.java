package ru.sooslick.qa.steps.browser;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.jetbrains.annotations.Nullable;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.exception.PageModelException;
import ru.sooslick.qa.core.helper.HtmlElementHelper;
import ru.sooslick.qa.core.helper.NameChainHelper;
import ru.sooslick.qa.pagemodel.ActionType;
import ru.sooslick.qa.pagemodel.HtmlElement;
import ru.sooslick.qa.steps.RepeatSteps;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

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
        HtmlElement element = findElement(elementName);
        if (element == null)
            throw new PageModelException("Unrecognized PageObject field: " + elementName, context.getLoadedPage().getClass());
        checkAllElementsVisible(Collections.singleton(element));
    }

    @Then("All elements from the following list are visible")
    public void checkAllElementsVisible(List<String> elementNames) {
        StringJoiner problemElements = new StringJoiner(", ");
        List<HtmlElement> elementsToCheck = new LinkedList<>();
        for (String elementName : elementNames) {
            HtmlElement element = findElement(elementName);
            if (element == null)
                problemElements.add(elementName);
            else
                elementsToCheck.add(element);
        }
        if (problemElements.length() != 0)
            throw new PageModelException("Unrecognized PageObject fields: " + problemElements, context.getLoadedPage().getClass());
        checkAllElementsVisible(elementsToCheck);
    }

    @Given("A user scrolls the page to element {string}")
    public void scrollToElement(String elementName) {
        // todo highly duplicated block of code
        HtmlElement element = findElement(elementName);
        if (element == null)
            throw new PageModelException("Unrecognized PageObject field: " + elementName, context.getLoadedPage().getClass());
        scrollToElement(element);
    }

    // todo move scroll steps to its own class and implement following steps:
    //  - scroll to top / bottom (left / right)
    //  - scroll N px up / down (left / right)
    //  - bonus: scroll inner element

    private @Nullable HtmlElement findElement(String elementName) {
        LinkedList<String> names = NameChainHelper.getChainLinks(elementName);
        return HtmlElementHelper.findElementByNameChain(context.getLoadedPage(), names);
    }
}
