package ru.sooslick.qa.core.webdriver;

import org.junit.platform.commons.util.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import ru.sooslick.qa.core.RunnerProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * Default Chrome config for web testing
 */
public class DefaultChromeConfiguration implements WebDriverConfig {

    private String emulatedDevice;

    @Override
    public WebDriver getDriver() {
        return createDriver(getChromeOptions(), RunnerProperties.WEBDRIVER_PATH);
    }

    @Override
    public void applyEmulation(String deviceType) {
        this.emulatedDevice = deviceType;
    }

    protected ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", Map.of(
                "download.default_directory", RunnerProperties.WEBDRIVER_DOWNLOADS_DIRECTORY
        ));
        chromeOptions.addArguments("--start-maximized");

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        chromeOptions.setCapability("goog:loggingPrefs", logPrefs);

        if (emulatedDevice != null) {
            Map<String, String> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceName", emulatedDevice);
            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        }

        if (!StringUtils.isBlank(RunnerProperties.BROWSER_BINARY_PATH))
            chromeOptions.setBinary(RunnerProperties.BROWSER_BINARY_PATH);
        return chromeOptions;
    }

    protected ChromeDriver createDriver(ChromeOptions chromeOptions, String pathToDriver) {
        System.setProperty("webdriver.chrome.driver", pathToDriver);
        return new ChromeDriver(chromeOptions);
    }
}
