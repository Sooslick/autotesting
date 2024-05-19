package ru.sooslick.qa.pagemodel.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.sooslick.qa.core.helper.WebDriverHelper;

/**
 * Default implementation for scrolling to the top of the page (using JS)
 */
public class DefaultScrollToTopAction implements ActionPerformer<Void> {
    @Override
    public Void perform(WebDriver driver, WebElement element) {
        WebDriverHelper.runJs(driver, "window.scrollTo(0, 0);");
        return null;
    }
}
