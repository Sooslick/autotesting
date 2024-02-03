package ru.sooslick.qa.pagemodel;

import java.util.Collection;

public interface ElementsContainer {
    String getName();

    HtmlElement getElementByName(String name);

    Collection<HtmlElement> getAllElements();

    void addElement(HtmlElement element);
}
