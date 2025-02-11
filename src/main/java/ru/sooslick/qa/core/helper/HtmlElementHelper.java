package ru.sooslick.qa.core.helper;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.sooslick.qa.core.exception.PageModelException;
import ru.sooslick.qa.pagemodel.ElementsContainer;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Helper class for {@link HtmlElement}s declared in various Page Objects.
 */
@UtilityClass
public class HtmlElementHelper {

    /**
     * Performs reflective check if field specified in method parameter is HtmlElement or child class.
     *
     * @param field parent object's field.
     * @return true if specified field is HtmlElement or child class.
     */
    public boolean isHtmlElement(Field field) {
        return HtmlElement.class.isAssignableFrom(field.getType());
    }

    /**
     * Performs reflective checks, is element's field HtmlElement,
     * for all fields of specified element instance.
     *
     * @param element element to check.
     * @return true if any of element's fields is HtmlElement or child class.
     */
    public boolean hasInnerElements(HtmlElement element) {
        return Arrays.stream(element.getClass().getFields())
                .anyMatch(HtmlElementHelper::isHtmlElement);
    }

    /**
     * Finds element with given name or name chain in desired container.
     * Method splits given name by nesting separator "->" and
     * looks for any HtmlElement having {@link ElementName} annotation or field.
     * <p>
     * For example, if we have Page with element "Header Block" which has child "Logo Icon",
     * we should use "Header Block -> Logo Icon".
     *
     * @param where       page or page block where we should search elements.
     * @param elementName desired element name or name chain, separated by "->" mark.
     * @return found element with given name.
     * @throws PageModelException if specified container has not any element with given name or name chain.
     */
    public @NotNull HtmlElement findElementByName(ElementsContainer where, String elementName) {
        LinkedList<String> names = NameChainHelper.getChainLinks(elementName);
        HtmlElement result = findElementByNameChain(where, names);
        if (result == null)
            throw new PageModelException("Unrecognized PageObject field: " + elementName, where.getClass());
        return result;
    }

    /**
     * Finds list of elements with given names or name chains in desired container.
     *
     * @param where        page or page block where we should search elements.
     * @param elementNames desired elements names or name chains.
     * @return list of elements with given names
     * @throws PageModelException if specified container has not any element with given name or name chain.
     * @see HtmlElementHelper#findElementByName(ru.sooslick.qa.pagemodel.ElementsContainer, java.lang.String)
     */
    public List<HtmlElement> findElementsByNames(ElementsContainer where, List<String> elementNames) {
        return elementNames.stream()
                .map(name -> HtmlElementHelper.findElementByName(where, name))
                .collect(Collectors.toList());
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
