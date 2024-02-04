package ru.sooslick.qa.core.page;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import ru.sooslick.qa.core.helper.ActionsHelper;
import ru.sooslick.qa.core.helper.HtmlElementHelper;
import ru.sooslick.qa.core.helper.PageAnnotationsHelper;
import ru.sooslick.qa.pagemodel.ElementsContainer;
import ru.sooslick.qa.pagemodel.HtmlElement;
import ru.sooslick.qa.pagemodel.annotations.Actions;

import java.lang.reflect.Field;

@Slf4j
@AllArgsConstructor
@RequiredArgsConstructor
public class PageFieldDecorator implements FieldDecorator {
    private final static int NESTING_LIMITER = 10;

    private final WebDriver webDriver;
    private final SearchContext parentElement;
    private final ElementsContainer container;   // todo I should rework decorator to remove all unnecessary parameters
    private int depth = 0;

    @Override
    @SneakyThrows
    public Object decorate(ClassLoader loader, Field field) {
        if (!HtmlElementHelper.isHtmlElement(field))
            return null;
        HtmlElement element = (HtmlElement) field.getType().getDeclaredConstructor().newInstance();
        decorateElement(element, field);
        container.addChildElement(element);

        if (HtmlElementHelper.hasInnerElements(element))
            decorateInnerElement(element);
        return element;
    }

    private void decorateElement(HtmlElement element, Field field) {
        // todo I should create factory and remove unwanted setters
        element.setDriver(webDriver);
        element.setName(PageAnnotationsHelper.getElementName(field));
        element.setLocator(PageAnnotationsHelper.getElementLocator(field));
        element.setRequired(PageAnnotationsHelper.getRequired(field));
        element.setParent(parentElement);
        element.setActions(ActionsHelper.createMapFromAnnotation(field.getAnnotation(Actions.class)));
        // todo unchecked annotations: ComponentLocator, ElementAttribute (currently unused)
        // todo implement RemoteWebElement features
    }

    private void decorateInnerElement(HtmlElement element) {
        if (depth >= NESTING_LIMITER) {
            log.warn("Can't fully load PageObject '{}', exceeded nesting limit.", element.getName());
            return;
        }
        PageFactory.initElements(new PageFieldDecorator(webDriver, element, element, depth + 1), element);
    }
}
