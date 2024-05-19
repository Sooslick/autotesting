package ru.sooslick.qa.pagemodel.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Default implementation for visibility check based on WebElement.isDisplayed()
 */
public class DefaultGetElementVisibilityAction implements ActionPerformer<Boolean> {
    @Override
    public Boolean perform(WebDriver driver, WebElement element) {
        return element.isDisplayed();
    }
}
