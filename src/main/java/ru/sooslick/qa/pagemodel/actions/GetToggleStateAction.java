package ru.sooslick.qa.pagemodel.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.sooslick.qa.core.helper.NumberHelper;

/**
 * Get Checkbox State implementatino for Sooslick.Art pages that uses custom toggles
 */
// todo move out of framework (too specific thing from my site)
public class GetToggleStateAction implements ActionPerformer<Boolean> {

    @Override
    public Boolean perform(WebDriver driver, WebElement element) {
        WebElement toggleThumb = element.findElement(By.xpath("./*[1]"));
        String left = toggleThumb.getCssValue("left");
        String probablyNumber = NumberHelper.extractNumber(left);
        return NumberHelper.tryParseInteger(probablyNumber, 0) > 2;
    }
}
