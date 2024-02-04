package ru.sooslick.qa.pagemodel;

import java.util.Collection;

public interface ElementsContainer {
    String getName();   // todo I probably should move this method to interface like NamedParameter

    HtmlElement getChildElementByName(String name);

    Collection<HtmlElement> getChildElements();

    void addChildElement(HtmlElement element);
}
