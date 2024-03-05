package ru.sooslick.qa.steps.browser;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import ru.sooslick.qa.core.NumberComparisonMethod;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.WebDriverProvider;
import ru.sooslick.qa.core.repeaters.Repeat;
import ru.sooslick.qa.pagemodel.annotations.Context;

public class BrowserSteps {

    @Context
    private ScenarioContext context;

    @Given("A user opens a new browser window and follows the link {string}")
    public void openBrowser(String url) {
        // todo check if driver session exist
        WebDriver webDriver = WebDriverProvider.getWebDriver();
        webDriver.manage().window().maximize();
        webDriver.get(url);
        context.setWebDriver(webDriver);
    }

    @Given("A user follows the link {string}")
    public void gotoUrl(String url) {
        context.getWebDriver().get(url);
    }

    @Given("A user clicks back button in browser toolbar")
    public void back() {
        context.getWebDriver().navigate().back();
    }

    @Then("The active tab has a title {string}")
    public void checkActiveTabTitle(String title) {
        WebDriver webDriver = context.getWebDriver();
        Repeat.untilSuccess(() -> Assertions.assertEquals(title, webDriver.getTitle()));
    }

    @Given("A user maximizes the browser window")
    public void maximizeBrowserWindow() {
        context.getWebDriver().manage().window().maximize();
    }

    @Then("Browser window width is {numberComparisonMethod} {dataGenerator} pixels")
    public void checkActiveWindowWidth(NumberComparisonMethod method, String value) {
        int expectedValue = Integer.parseInt(value);
        WebDriver webDriver = context.getWebDriver();
        method.test(expectedValue, webDriver.manage().window().getSize().getWidth());
    }

    @Given("A user sets browser window width to {dataGenerator} pixels")
    public void setBrowserWindowWidth(String width) {
        int expectedWidth = Integer.parseInt(width);
        WebDriver.Window window = context.getWebDriver().manage().window();
        window.setSize(new Dimension(
                expectedWidth,
                window.getSize().getHeight()));
    }

    @After
    public void closeAllBrowsers() {
        WebDriver webDriver = context.getWebDriver();
        if (webDriver != null)
            webDriver.quit();
        this.context.setWebDriver(null);
    }
}
