package ru.sooslick.qa.pagemodel.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Default implementation for extracting text from the element using WebElement.getText()
 */
public class DefaultGetTextAction implements ActionPerformer<String> {

    @Override
    public String perform(WebDriver driver, WebElement element) {
        return element.getText();
    }
}
