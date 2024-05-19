package ru.sooslick.qa.pagemodel.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Default implementation for scrolling to the element
 */
public class DefaultScrollToElementAction implements ActionPerformer<Void> {
    @Override
    public Void perform(WebDriver driver, WebElement element) {
        new Actions(driver)
                .scrollToElement(element)
                .build()
                .perform();
        return null;
    }
}
