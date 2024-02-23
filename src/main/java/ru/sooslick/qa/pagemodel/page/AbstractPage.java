package ru.sooslick.qa.pagemodel.page;

import ru.sooslick.qa.core.helper.PageAnnotationsHelper;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

abstract public class AbstractPage implements Page {
    private final Map<String, HtmlElement> innerElements = new HashMap<>();

    @Override
    public String getName() {
        return PageAnnotationsHelper.getPageName(this.getClass());
    }

    @Override
    public HtmlElement getChildElementByName(String name) {
        return innerElements.get(name);
    }

    @Override
    public Collection<HtmlElement> getChildElements() {
        return innerElements.values();
    }

    @Override
    public void addChildElement(HtmlElement element) {
        innerElements.put(element.getName(), element);
    }
}
