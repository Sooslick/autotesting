package ru.sooslick.qa.steps.browser;

import io.cucumber.java.en.Then;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.page.PageLoader;
import ru.sooslick.qa.pagemodel.annotations.Context;
import ru.sooslick.qa.pagemodel.element.HtmlElement;
import ru.sooslick.qa.pagemodel.page.Page;

import java.util.LinkedList;

public class PageSteps {

    @Context
    private ScenarioContext context;

    @Then("{string} page opens")
    public void openPage(String name) {
        Page page = PageLoader.loadPage(context.getWebDriver(), name);
        context.setLoadedPage(page);
        checkRequiredElementsVisible(page);
    }

    private void checkRequiredElementsVisible(Page page) {
        LinkedList<HtmlElement> requiredElements = new LinkedList<>();
        LinkedList<HtmlElement> elementsToCheck = new LinkedList<>(page.getChildElements());
        while (!elementsToCheck.isEmpty()) {
            HtmlElement checkedElement = elementsToCheck.removeFirst();
            if (checkedElement.isRequired())
                requiredElements.add(checkedElement);
            elementsToCheck.addAll(checkedElement.getChildElements());
        }
        ElementSteps.checkAllElementsVisible(requiredElements);
    }
}
