package ru.sooslick.qa.core.webdriver;

import org.openqa.selenium.WebDriver;

public interface WebDriverConfig {
    WebDriver getDriver(String pathToDriver);
}
