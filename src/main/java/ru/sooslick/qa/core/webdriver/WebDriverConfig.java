package ru.sooslick.qa.core.webdriver;

import org.openqa.selenium.WebDriver;

/**
 * Interface for various WebDriver presets, providing configured and started WebDriver instances.
 */
public interface WebDriverConfig {

    /**
     * Configures and starts WebDriver with executable located at pathToDriver.
     *
     * @param pathToDriver path to webdriver executable.
     * @return WebDriver instance.
     */
    // todo I can remove pathToDriver param bcs this path exists in Runner yaml
    WebDriver getDriver(String pathToDriver);
}
