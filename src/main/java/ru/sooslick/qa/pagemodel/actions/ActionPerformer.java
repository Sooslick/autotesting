package ru.sooslick.qa.pagemodel.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Interface for various user actions on web pages and its elements.
 *
 * @param <ReturnType> Java type for values returned as result of performed action.
 */
public interface ActionPerformer<ReturnType> {

    /**
     * Performs the action and returns its result.
     *
     * @param driver  webdriver that will perform the action
     * @param element target element for action.
     * @return result of performed action.
     */
    ReturnType perform(WebDriver driver, WebElement element);
}
