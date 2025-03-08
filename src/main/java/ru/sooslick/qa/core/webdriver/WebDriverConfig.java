package ru.sooslick.qa.core.webdriver;

import org.openqa.selenium.WebDriver;

/**
 * Interface for various WebDriver presets, providing configured and started WebDriver instances.
 */
public interface WebDriverConfig {

    /**
     * Configures and starts WebDriver with executable located at test-run/web-driver/path runner variable.
     *
     * @return WebDriver instance.
     */
    WebDriver getDriver();

    /**
     * Tells to webdriver which mobile device it should emulate
     *
     * @param deviceType desired mobile device
     */
    void applyEmulation(String deviceType);
}
