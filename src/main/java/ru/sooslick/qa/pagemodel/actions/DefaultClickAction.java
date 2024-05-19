package ru.sooslick.qa.pagemodel.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Default implementation for LMB click on element using WebElement.click()
 */
public class DefaultClickAction implements ActionPerformer<Void> {
    @Override
    public Void perform(WebDriver driver, WebElement element) {
        element.click();
        return null;
    }
}
