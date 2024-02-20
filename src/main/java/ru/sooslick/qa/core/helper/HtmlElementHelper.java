package ru.sooslick.qa.core.helper;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.sooslick.qa.core.exception.PageModelException;
import ru.sooslick.qa.pagemodel.ElementsContainer;
import ru.sooslick.qa.pagemodel.HtmlElement;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;

@UtilityClass
public class HtmlElementHelper {
    public boolean isHtmlElement(Field field) {
        return HtmlElement.class.isAssignableFrom(field.getType());
    }

    public boolean hasInnerElements(HtmlElement element) {
        return Arrays.stream(element.getClass().getFields())
                .anyMatch(HtmlElementHelper::isHtmlElement);
    }

    public @NotNull HtmlElement findElementByName(ElementsContainer where, String elementName) {
        LinkedList<String> names = NameChainHelper.getChainLinks(elementName);
        HtmlElement result = findElementByNameChain(where, names);
        if (result == null)
            throw new PageModelException("Unrecognized PageObject field: " + elementName, where.getClass());
        return result;
    }

    @Contract(mutates = "param2")
    private @Nullable HtmlElement findElementByNameChain(ElementsContainer where, LinkedList<String> chain) {
        if (chain.isEmpty())
            return null;
        String name = chain.removeFirst();
        HtmlElement foundElement = where.getChildElementByName(name);
        return chain.isEmpty() ? foundElement : findElementByNameChain(foundElement, chain);
    }
}
