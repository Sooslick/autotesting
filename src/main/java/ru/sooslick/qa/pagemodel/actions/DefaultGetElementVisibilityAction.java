package ru.sooslick.qa.pagemodel.actions;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Default implementation for visibility check based on WebElement.isDisplayed()
 */
public class DefaultGetElementVisibilityAction implements ActionPerformer<Boolean> {
    @Override
    public Boolean perform(WebDriver driver, WebElement element) {
        if (element.isDisplayed()) {
            Dimension dimension = element.getSize();
            return (dimension.getWidth() != 0 && dimension.getHeight() != 0);
        }
        return false;
    }
}
