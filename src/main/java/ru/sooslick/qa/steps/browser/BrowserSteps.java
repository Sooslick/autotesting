package ru.sooslick.qa.steps.browser;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.WebDriverProvider;

public class BrowserSteps {
    private ScenarioContext context;

    // todo probably I should abstractize context stuff
    @Before
    public void updateContext(Scenario scenario) {
        if (context == null)
            context = ScenarioContext.getContext(scenario);
    }

    @Given("A user opens a new browser window and follows the link {string}")
    public void openBrowser(String url) {
        WebDriver webDriver = WebDriverProvider.getWebDriver();
        webDriver.get(url);
        this.context.setWebDriver(webDriver);
    }

    @After
    public void closeAllBrowsers() {
        WebDriver webDriver = context.getWebDriver();
        if (webDriver != null)
            webDriver.quit();
        this.context.setWebDriver(null);
        // todo probably I should add conditional @UI hook
    }
}
