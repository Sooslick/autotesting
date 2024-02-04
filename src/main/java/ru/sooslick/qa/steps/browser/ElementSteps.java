package ru.sooslick.qa.steps.browser;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
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

public class ElementSteps {
    private ScenarioContext context;

    // todo remove if unused
    public static void checkElementVisible(HtmlElement element) {
        checkAllElementsVisible(Collections.singleton(element));
    }

    public static void checkAllElementsVisible(Collection<HtmlElement> elementCollection) {
        RepeatSteps.forEachUntilSuccess(elementCollection, (element) ->
                element.triggerAction(ActionType.VISIBILITY_CHECK));
    }

    // todo probably I should abstractize context stuff
    @Before
    public void updateContext(Scenario scenario) {
        if (context == null)
            context = ScenarioContext.getContext(scenario);
    }

    @Then("Element {string} is visible")
    public void checkElementVisible(String elementName) {
        LinkedList<String> names = NameChainHelper.getChainLinks(elementName);
        HtmlElement element = HtmlElementHelper.findElementByNameChain(context.getLoadedPage(), names);
        if (element == null)
            throw new PageModelException("Unrecognized PageObject field: " + elementName, context.getLoadedPage().getClass());
        checkElementVisible(element);
    }
}
