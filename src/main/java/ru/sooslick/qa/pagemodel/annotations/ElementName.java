package ru.sooslick.qa.pagemodel.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * HtmlElement annotation for naming HtmlElements to referring from cucumber scenarios.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface ElementName {

    /**
     * @return name of annotated HtmlElement for using in cucumber scenarios.
     */
    String value();
}
