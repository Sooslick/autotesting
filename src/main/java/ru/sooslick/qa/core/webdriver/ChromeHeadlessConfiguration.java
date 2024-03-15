package ru.sooslick.qa.core.webdriver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeHeadlessConfiguration extends DefaultChromeConfiguration {

    @Override
    public WebDriver getDriver(String pathToDriver) {
        ChromeOptions chromeOptions = getChromeOptions();
        chromeOptions.addArguments("--headless=new");
        WebDriver webDriver = createDriver(chromeOptions, pathToDriver);
        webDriver.manage().window().setSize(new Dimension(1280, 720));
        return webDriver;
    }
}
