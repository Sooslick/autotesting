package ru.sooslick.qa.core.webdriver;

import org.junit.platform.commons.util.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.sooslick.qa.core.RunnerProperties;

import java.util.Map;

public class DefaultChromeConfiguration implements WebDriverConfig {

    @Override
    public WebDriver getDriver(String pathToDriver) {
        WebDriver webDriver = createDriver(getChromeOptions(), pathToDriver);
        webDriver.manage().window().maximize();
        return webDriver;
    }

    protected ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", Map.of(
                // todo generate unique downloads folder
                "download.default_directory", "downloads"
        ));
        if (!StringUtils.isBlank(RunnerProperties.BROWSER_BINARY_PATH))
            chromeOptions.setBinary(RunnerProperties.BROWSER_BINARY_PATH);
        return chromeOptions;
    }

    protected ChromeDriver createDriver(ChromeOptions chromeOptions, String pathToDriver) {
        System.setProperty("webdriver.chrome.driver", pathToDriver);
        return new ChromeDriver(chromeOptions);
    }
}
