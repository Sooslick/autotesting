package ru.sooslick.qa.core;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

@RequiredArgsConstructor
public enum Alignment {
    TOP(el -> el.getLocation().getY()),
    BOTTOM(el -> el.getLocation().getY() + el.getSize().getHeight()),
    LEFT(el -> el.getLocation().getX()),
    RIGHT(el -> el.getLocation().getX() + el.getSize().getWidth());

    private final Function<WebElement, Integer> f;

    public int getBaseline(WebElement element) {
        return f.apply(element);
    }
}
