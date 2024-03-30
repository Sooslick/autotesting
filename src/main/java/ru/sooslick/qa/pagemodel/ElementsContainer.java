package ru.sooslick.qa.pagemodel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.sooslick.qa.core.NamedParameter;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

import java.util.Collection;

/**
 * Interface for various objects that may contain HtmlElements, such as pages or page blocks.
 */
public interface ElementsContainer extends NamedParameter {

    /**
     * @param name Name of required element
     * @return element from this container with given name or null if there are no element with given name.
     */
    @Nullable HtmlElement getChildElementByName(String name);

    /**
     * @return collection of all elements in this container.
     */
    @NotNull Collection<HtmlElement> getChildElements();

    /**
     * Adds element to this container.
     *
     * @param element HtmlElement to be added.
     */
    void addChildElement(@NotNull HtmlElement element);
}
