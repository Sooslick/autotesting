package ru.sooslick.qa.core.helper;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Helper class for WebDrivers and related actions.
 */
@UtilityClass
public class WebDriverHelper {

    /**
     * Executes piece of javascript code using webdriver.
     *
     * @param driver          webdriver that will execute javascript.
     * @param js              javascript code.
     * @param scriptArguments arguments to be passed to javascript.
     * @return value, returned by executed javascript code.
     */
    public Object runJs(WebDriver driver, String js, Object... scriptArguments) {
        if (driver instanceof JavascriptExecutor jsExecutor) {
            return jsExecutor.executeScript(js, scriptArguments);
        }
        throw new UnsupportedOperationException("Used WebDriver don't support javascript execution");
    }
}
