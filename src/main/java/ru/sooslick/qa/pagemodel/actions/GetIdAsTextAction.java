package ru.sooslick.qa.pagemodel.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * GET_TEXT implementation that uses id attribute as element text
 */
public class GetIdAsTextAction implements ActionPerformer<String> {

    @Override
    public String perform(WebDriver driver, WebElement element) {
        return element.getAttribute("id");
    }
}
