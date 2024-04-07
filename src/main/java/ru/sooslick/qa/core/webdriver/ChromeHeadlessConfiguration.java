package ru.sooslick.qa.core.webdriver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.sooslick.qa.core.RunnerProperties;

/**
 * Fork of default Chrome config with --headless arg
 */
public class ChromeHeadlessConfiguration extends DefaultChromeConfiguration {

    @Override
    public WebDriver getDriver() {
        ChromeOptions chromeOptions = getChromeOptions();
        chromeOptions.addArguments("--headless=new");
        WebDriver webDriver = createDriver(chromeOptions, RunnerProperties.WEBDRIVER_PATH);
        webDriver.manage().window().setSize(new Dimension(1280, 720));
        return webDriver;
    }
}
