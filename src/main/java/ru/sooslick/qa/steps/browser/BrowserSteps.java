package ru.sooslick.qa.steps.browser;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import ru.sooslick.qa.core.NumberComparisonMethod;
import ru.sooslick.qa.core.RunnerProperties;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.repeaters.Repeat;
import ru.sooslick.qa.core.webdriver.WebDriverConfigurationResolver;
import ru.sooslick.qa.pagemodel.annotations.Context;

import java.io.File;

public class BrowserSteps {

    @Context
    private ScenarioContext context;

    @Given("A user opens a new browser window")
    public void openBrowser() {
        WebDriver webDriver = context.getWebDriver();
        if (webDriver == null) {
            webDriver = WebDriverConfigurationResolver.getWebDriver(null);
            context.setWebDriver(webDriver);
        } else {
            webDriver.switchTo().newWindow(WindowType.WINDOW);
        }
    }

    @Given("A user opens a new browser window and follows the link {dataGenerator}")
    public void openBrowser(String url) {
        WebDriver webDriver = context.getWebDriver();
        if (webDriver == null) {
            webDriver = WebDriverConfigurationResolver.getWebDriver(null);
            webDriver.get(url);
            context.setWebDriver(webDriver);
        } else {
            webDriver.switchTo().newWindow(WindowType.WINDOW);
            webDriver.get(url);
        }
    }

    @Given("A user opens a new browser tab and follows the link {dataGenerator}")
    public void openTab(String url) {
        WebDriver webDriver = context.getWebDriver();
        webDriver.switchTo().newWindow(WindowType.TAB);
        webDriver.get(url);
    }

    @Given("A user opens a new browser window, emulating device {string}, and follows the link {dataGenerator}")
    public void openBrowserAsMobile(String deviceType, String url) {
        WebDriver webDriver = context.getWebDriver();
        // tell me if I can apply emulation properties without restarting browser (selenide can do it, right?)
        if (webDriver != null)
            webDriver.quit();
        webDriver = WebDriverConfigurationResolver.getWebDriver(deviceType);
        webDriver.get(url);
        context.setWebDriver(webDriver);
    }

    @Given("A user follows the link {dataGenerator}")
    public void gotoUrl(String url) {
        context.getWebDriver().get(url);
    }

    @Given("A user clicks back button in browser toolbar")
    public void back() {
        context.getWebDriver().navigate().back();
    }

    @Then("The active tab has a title {dataGenerator}")
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

    @Given("A user sets browser window size to {int}, {int} pixels")
    public void setBrowserWindowSize(int width, int height) {
        WebDriver.Window window = context.getWebDriver().manage().window();
        window.setSize(new Dimension(width, height));
    }

    @After
    public void closeAllBrowsers() {
        WebDriver webDriver = context.getWebDriver();
        if (webDriver != null) {
            takeScreenshot();
            webDriver.quit();
        }
        this.context.setWebDriver(null);
        FileUtils.deleteQuietly(new File(RunnerProperties.WEBDRIVER_DOWNLOADS_DIRECTORY));
    }

    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "Screenshot", type = "image/png", fileExtension = ".png")
    private byte[] takeScreenshot() {
        return ((TakesScreenshot) context.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
