package ru.sooslick.qa.pagemodel.components;

import org.openqa.selenium.By;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

// todo
//  1: javadoc
//  2: proper classnames (enum Component)
//  3: init properly during page decoration
public record ComputedComponent(Component type, By locator, Class<? extends HtmlElement> containerType) {

    @Override
    public String toString() {
        return String.format("%s (java type %s.class, found by %s)", type, containerType.getSimpleName(), locator);
    }
}
