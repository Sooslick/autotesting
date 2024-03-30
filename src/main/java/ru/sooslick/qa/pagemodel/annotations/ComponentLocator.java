package ru.sooslick.qa.pagemodel.annotations;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.components.Component;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * HtmlElement annotation for specifying locators to various parts of elements.
 */
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ComponentLocators.class)
@Target(ElementType.FIELD)
public @interface ComponentLocator {

    /**
     * @return Type of element's component.
     */
    Component component();

    /**
     * @return Java type with component's Page Object.
     */
    Class<? extends HtmlElement> type() default HtmlElement.class;

    /**
     * @return locator to find this component as child element of annotated HtmlElement.
     */
    FindBy locator();
}
