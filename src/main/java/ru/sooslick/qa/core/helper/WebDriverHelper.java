package ru.sooslick.qa.core.helper;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

@UtilityClass
public class WebDriverHelper {

    public Object runJs(WebDriver driver, String js, Object... scriptArguments) {
        if (driver instanceof JavascriptExecutor jsExecutor) {
            return jsExecutor.executeScript(js, scriptArguments);
        }
        throw new UnsupportedOperationException("Used WebDriver don't support javascript execution");
    }
}
