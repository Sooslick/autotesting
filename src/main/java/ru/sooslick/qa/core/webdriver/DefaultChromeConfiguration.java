package ru.sooslick.qa.core.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Map;

public class DefaultChromeConfiguration implements WebDriverConfig {

    @Override
    public WebDriver getDriver(String pathToDriver) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", Map.of(
                // todo generate unique downloads folder
                "download.default_directory", "downloads"
        ));
        System.setProperty("webdriver.chrome.driver", pathToDriver);
        return new ChromeDriver(chromeOptions);
    }
}
