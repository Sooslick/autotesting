package ru.sooslick.qa.pagemodel.annotations;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.components.Component;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ComponentLocators.class)
public @interface ComponentLocator {
    Component component();

    Class<? extends HtmlElement> type() default HtmlElement.class;

    FindBy locator();
}
