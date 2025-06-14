package ru.sooslick.qa.pagemodel.actions;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Implementation for element visibility that checks element is fully visible inside viewport
 */
public class IsElementInViewportCheck implements ActionPerformer<Boolean> {
    @Override
    public Boolean perform(WebDriver driver, WebElement element) {
        if (element.isDisplayed()) {
            Dimension size = element.getSize();
            if (size.getWidth() == 0 || size.getHeight() == 0)
                return false;

            JavascriptExecutor jsexec = (JavascriptExecutor) driver;
            long left = (long) jsexec.executeScript("return window.pageXOffset");
            long top = (long) jsexec.executeScript("return window.pageYOffset");
            long right = left + (long) jsexec.executeScript("return window.innerWidth");
            long bottom = top + (long) jsexec.executeScript("return window.innerHeight");

            Point point = element.getLocation();
            int x = point.getX();
            int y = point.getY();
            int w = size.getWidth();
            int h = size.getHeight();
            return (x >= left && y >= top && x + w <= right && y + h <= bottom);
        }

        return false;
    }
}
