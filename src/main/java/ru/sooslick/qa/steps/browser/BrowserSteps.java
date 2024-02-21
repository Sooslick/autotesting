package ru.sooslick.qa.steps.browser;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.WebDriverProvider;
import ru.sooslick.qa.core.repeaters.Repeat;
import ru.sooslick.qa.pagemodel.annotations.Context;

public class BrowserSteps {

    @Context
    private ScenarioContext context;

    @Given("A user opens a new browser window and follows the link {string}")
    public void openBrowser(String url) {
        WebDriver webDriver = WebDriverProvider.getWebDriver();
        webDriver.manage().window().maximize();
        webDriver.get(url);
        this.context.setWebDriver(webDriver);
    }

    @Then("The active tab has a title {string}")
    public void checkActiveTabTitle(String title) {
        WebDriver webDriver = context.getWebDriver();
        Repeat.untilSuccess(() -> Assertions.assertEquals(title, webDriver.getTitle()));
    }

    @After
    public void closeAllBrowsers() {
        WebDriver webDriver = context.getWebDriver();
        if (webDriver != null)
            webDriver.quit();
        this.context.setWebDriver(null);
    }
}
