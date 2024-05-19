package ru.sooslick.qa.pagemodel.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Default implementation for determining is element checked or not, based on 'checked' attribute
 */
public class DefaultGetCheckboxStateAction implements ActionPerformer<Boolean> {

    @Override
    public Boolean perform(WebDriver driver, WebElement element) {
        return element.getAttribute("checked") != null;
    }
}
