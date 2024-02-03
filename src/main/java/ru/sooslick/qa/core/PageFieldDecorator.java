package ru.sooslick.qa.core;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import ru.sooslick.qa.pagemodel.ElementsContainer;
import ru.sooslick.qa.pagemodel.HtmlElement;

import java.lang.reflect.Field;

@Slf4j
record PageFieldDecorator(SearchContext searchContext, ElementsContainer container, int depth) implements FieldDecorator {
    private final static int NESTING_LIMITER = 10;

    // todo probably I should declare ElementsContainer as SearchContext
    PageFieldDecorator(SearchContext searchContext, ElementsContainer container) {
        this(searchContext, container, 0);
    }

    @Override
    @SneakyThrows
    public Object decorate(ClassLoader loader, Field field) {
        if (!HtmlElementUtils.isHtmlElement(field))
            return null;
        HtmlElement element = (HtmlElement) field.getType().getDeclaredConstructor().newInstance();
        decorateElement(element, field);
        container.addElement(element);

        if (HtmlElementUtils.hasInnerElements(element))
            decorateInnerElement(element);
        return element;
    }

    private void decorateElement(HtmlElement element, Field field) {
        element.setName(PageModelAnnotationsUtils.getElementName(field));
        element.setLocator(PageModelAnnotationsUtils.getElementLocator(field));
        element.setRequired(PageModelAnnotationsUtils.getRequired(field));
        element.setParent(searchContext);
        // todo unchecked annotations: ComponentLocator, ElementAttribute (currently unused)
    }

    private void decorateInnerElement(HtmlElement element) {
        if (depth >= NESTING_LIMITER) {
            log.warn("Can't fully load PageObject '{}', exceeded nesting limit.", element.getName());
            return;
        }
        PageFactory.initElements(new PageFieldDecorator(element, element, depth + 1), element);
    }
}
