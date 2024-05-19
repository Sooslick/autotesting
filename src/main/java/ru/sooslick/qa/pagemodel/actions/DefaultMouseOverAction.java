package ru.sooslick.qa.pagemodel.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Default implementation for hovering element with mouse cursor
 */
public class DefaultMouseOverAction implements ActionPerformer<Void> {
    @Override
    public Void perform(WebDriver driver, WebElement element) {
        new Actions(driver)
                .moveToElement(element)
                .build()
                .perform();
        return null;
    }
}
