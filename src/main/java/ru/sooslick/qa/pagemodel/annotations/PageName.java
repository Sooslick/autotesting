package ru.sooslick.qa.pagemodel.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Type annotation for naming Page name to referring from cucumber scenarios.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PageName {

    /**
     * @return name of annotated Page Object class for using in cucumber scenarios.
     */
    String value();
}
