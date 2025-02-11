package ru.sooslick.qa.pagemodel.page;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.NoSuchElementException;
import ru.sooslick.qa.core.helper.PageAnnotationsHelper;
import ru.sooslick.qa.pagemodel.ElementsContainer;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Abstract class for any pages in Page Model, providing default implementation for methods from {@link ElementsContainer}
 */
abstract public class AbstractPage implements Page {
    private final Map<String, HtmlElement> innerElements = new HashMap<>();

    @Override
    public String getName() {
        return PageAnnotationsHelper.getPageName(this.getClass());
    }

    @Override
    public @NotNull HtmlElement getChildElementByName(String name) {
        return Optional.ofNullable(innerElements.get(name))
                .orElseThrow(() -> new NoSuchElementException(String.format(
                        "Page %s has no known element with name '%s'\nCheck your @ElementName has no typos and your elements are HtmlElement (or it's subclass)",
                        this.getName(), name)));
    }

    @Override
    public @NotNull Collection<HtmlElement> getChildElements() {
        return innerElements.values();
    }

    @Override
    public void addChildElement(@NotNull HtmlElement element) {
        innerElements.put(element.getName(), element);
    }
}
