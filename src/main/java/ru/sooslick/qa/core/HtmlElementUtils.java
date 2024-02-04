package ru.sooslick.qa.core;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import ru.sooslick.qa.pagemodel.ElementsContainer;
import ru.sooslick.qa.pagemodel.HtmlElement;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;

@UtilityClass
public class HtmlElementUtils {
    public boolean isHtmlElement(Field field) {
        return HtmlElement.class.isAssignableFrom(field.getType());
    }

    public boolean hasInnerElements(HtmlElement element) {
        return Arrays.stream(element.getClass().getFields())
                .anyMatch(HtmlElementUtils::isHtmlElement);
    }

    @Contract(mutates = "param2")
    public @Nullable HtmlElement findElementByNameChain(ElementsContainer where, LinkedList<String> chain) {
        if (chain.isEmpty())
            return null;
        String name = chain.removeFirst();
        HtmlElement foundElement = where.getChildElementByName(name);
        return chain.isEmpty() ? foundElement : findElementByNameChain(foundElement, chain);
    }
}
