package ru.sooslick.qa.steps.browser;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import ru.sooslick.qa.core.PageLoader;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.pagemodel.HtmlElement;
import ru.sooslick.qa.pagemodel.Page;

import java.util.LinkedList;

public class PageSteps {
    private ScenarioContext context;

    @Before
    public void updateContext(Scenario scenario) {
        if (context == null)
            context = ScenarioContext.getContext(scenario);
    }

    @Then("{string} page opens")
    public void openPage(String name) {
        Page page = PageLoader.getInstance().loadPage(context.getWebDriver(), name);
        context.setLoadedPage(page);
        checkRequiredElementsVisible(page);
    }

    private void checkRequiredElementsVisible(Page page) {
        LinkedList<HtmlElement> requiredElements = new LinkedList<>();
        LinkedList<HtmlElement> elementsToCheck = new LinkedList<>(page.getAllElements());
        while (!elementsToCheck.isEmpty()) {
            HtmlElement checkedElement = elementsToCheck.removeFirst();
            if (checkedElement.isRequired())
                requiredElements.add(checkedElement);
            elementsToCheck.addAll(checkedElement.getAllElements());
        }
        ElementSteps.checkAllElementsVisible(requiredElements);
    }
}
