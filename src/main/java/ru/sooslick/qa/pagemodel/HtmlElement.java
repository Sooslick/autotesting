package ru.sooslick.qa.pagemodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class HtmlElement extends RemoteWebElement implements ElementsContainer {
    private final Map<String, HtmlElement> innerElements = new HashMap<>();

    private SearchContext parent;
    private String name;
    private By locator;
    private boolean required;

    @Override
    public HtmlElement getElementByName(String name) {
        return innerElements.get(name);
    }

    @Override
    public Collection<HtmlElement> getAllElements() {
        return innerElements.values();
    }

    @Override
    public void addElement(HtmlElement element) {
        innerElements.put(element.getName(), element);
    }
}
