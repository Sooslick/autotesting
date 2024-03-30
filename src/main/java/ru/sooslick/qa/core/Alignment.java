package ru.sooslick.qa.core;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

/**
 * Alignment enumeration with some associated functions.
 */
@RequiredArgsConstructor
public enum Alignment {
    TOP(el -> el.getLocation().getY()),
    BOTTOM(el -> el.getLocation().getY() + el.getSize().getHeight()),
    LEFT(el -> el.getLocation().getX()),
    RIGHT(el -> el.getLocation().getX() + el.getSize().getWidth());

    private final Function<WebElement, Integer> getBaselineFunction;

    /**
     * Returns coordinate of element's border.
     *
     * @param element html element to check.
     * @return calculated coordinate on the web-page.
     */
    public int getBaseline(WebElement element) {
        return getBaselineFunction.apply(element);
    }
}
