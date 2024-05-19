package ru.sooslick.qa.pagemodel.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Default implementation for LMB click and hold on element
 */
public class DefaultClickAndHoldAction implements ActionPerformer<Void> {
    @Override
    public Void perform(WebDriver driver, WebElement element) {
        new Actions(driver)
                .clickAndHold(element)
                .build()
                .perform();
        return null;
    }
}
