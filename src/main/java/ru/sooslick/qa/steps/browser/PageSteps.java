package ru.sooslick.qa.steps.browser;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.AssertionFailureBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.helper.WebDriverHelper;
import ru.sooslick.qa.core.page.PageLoader;
import ru.sooslick.qa.pagemodel.annotations.Context;
import ru.sooslick.qa.pagemodel.element.HtmlElement;
import ru.sooslick.qa.pagemodel.page.Page;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class PageSteps {

    @Context
    private ScenarioContext context;

    @Then("{string} page opens")
    public void openPage(String name) {
        Page page = PageLoader.loadPage(context.getWebDriver(), name);
        context.setLoadedPage(page);
        checkRequiredElementsVisible(page);
    }

    // todo extend usage, support any attributes (and probably any head tags)
    @Then("The page has header meta with name {string} and value {dataGenerator}")
    public void checkPageHeader(String name, String content) {
        WebDriver driver = context.getWebDriver();
        List<WebElement> probablyMetas = driver.findElements(By.xpath("//head//meta"));
        String expected = name + "=" + content;
        List<String> actual = probablyMetas.stream()
                .map(element -> element.getAttribute("name") + "=" + element.getAttribute("content"))
                .collect(Collectors.toList());
        if (!actual.contains(expected))
            AssertionFailureBuilder.assertionFailure()
                    .message("Page does not contain meta tag with given attributes!")
                    .expected(expected)
                    .actual(actual)
                    .includeValuesInMessage(true)
                    .buildAndThrow();
    }

    @Given("A user scrolls the page to the top of the page")
    public void scrollToTop() {
        WebDriverHelper.runJs(context.getWebDriver(), "window.scrollTo(0, 0);");
    }

    private void checkRequiredElementsVisible(Page page) {
        LinkedList<HtmlElement> requiredElements = new LinkedList<>();
        LinkedList<HtmlElement> elementsToCheck = new LinkedList<>(page.getChildElements());
        while (!elementsToCheck.isEmpty()) {
            HtmlElement checkedElement = elementsToCheck.removeFirst();
            if (checkedElement.isRequired()) {
                requiredElements.add(checkedElement);
                elementsToCheck.addAll(checkedElement.getChildElements());
            }
        }
        ElementSteps.checkAllElementsVisible(requiredElements);
    }
}
