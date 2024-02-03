package ru.sooslick.qa.core;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Map;

@UtilityClass
public class WebDriverProvider {

    public WebDriver getWebDriver() {
        // todo resolve config and load preferred config
        // todo generate unique downloads folder
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", Map.of(
                "download.default_directory", "downloads"
        ));
        System.setProperty("webdriver.chrome.driver", "E:/Soos/PROG/chromedriver.exe");
        return new ChromeDriver(chromeOptions);
    }
}
