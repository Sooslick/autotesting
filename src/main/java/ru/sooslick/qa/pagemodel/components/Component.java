package ru.sooslick.qa.pagemodel.components;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;

/**
 * Enumeration of various HtmlElement parts, used in page model and related steps.
 */
@RequiredArgsConstructor
@Getter
public enum Component {
    LI_ELEMENT(By.xpath("./li"), DefaultLiComponent.class);

    private final By defaultLocator;
    private final Class<?> containerType;
}
