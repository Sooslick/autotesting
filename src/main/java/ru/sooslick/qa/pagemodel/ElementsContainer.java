package ru.sooslick.qa.pagemodel;

import ru.sooslick.qa.core.NamedParameter;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

import java.util.Collection;

public interface ElementsContainer extends NamedParameter {

    HtmlElement getChildElementByName(String name);

    Collection<HtmlElement> getChildElements();

    void addChildElement(HtmlElement element);
}
