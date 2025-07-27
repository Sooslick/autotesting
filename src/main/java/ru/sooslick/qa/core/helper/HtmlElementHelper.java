package ru.sooslick.qa.core.helper;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.platform.commons.util.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import ru.sooslick.qa.core.exception.PageModelException;
import ru.sooslick.qa.core.page.HtmlElementBuilder;
import ru.sooslick.qa.core.page.PageFieldDecorator;
import ru.sooslick.qa.pagemodel.ElementsContainer;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.components.Component;
import ru.sooslick.qa.pagemodel.components.ComputedComponent;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
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

    /**
     * Create HtmlElement from child WebElement, found by specified component locator,
     * and init all inner elements as nested page block
     *
     * @param parent        HtmlElement that has component
     * @param componentType type of inner structure element
     * @return wrapped inner element
     */
    // todo: review wrapped component code in other placec
    public HtmlElement wrapElement(HtmlElement parent, Component componentType) {
        ComputedComponent component = parent.getComponent(componentType);
        HtmlElement wrappedElement;
        WebDriver webDriver = parent.getWrappedDriver();
        try {
            wrappedElement = new HtmlElementBuilder(component.containerType())
                    .webDriver(webDriver)
                    .parent(parent)
                    .locator(component.locator())
                    .build();
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException("Element " + parent.getName() + " has invalid component " + component);
        }
        FieldDecorator decorator = new PageFieldDecorator(webDriver, wrappedElement, wrappedElement);
        PageFactory.initElements(decorator, wrappedElement);
        return wrappedElement;
    }

    /**
     * Performs a check that given names are valid for used element's component,
     * and throw IllegalStateException if Page Object does not contain at least one of provided names
     *
     * @param componentPageObjectClass Page Object that contains some elements
     * @param elementNames             element names to check
     */
    public void validateComponentElements(Class<? extends HtmlElement> componentPageObjectClass, Collection<String> elementNames) {
        List<String> existingNames = PageAnnotationsHelper.getElementNames(componentPageObjectClass);
        String unknowns = elementNames.stream()
                .map(NameChainHelper::getFirst)
                .distinct()
                .filter(probablyName -> !existingNames.contains(probablyName))
                .collect(Collectors.joining(", "));
        if (!StringUtils.isBlank(unknowns))
            throw new IllegalArgumentException("Elements " + unknowns + " does not declared inside component type " + componentPageObjectClass.getName());
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
