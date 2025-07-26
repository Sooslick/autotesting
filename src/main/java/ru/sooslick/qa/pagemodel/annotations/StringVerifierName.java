package ru.sooslick.qa.pagemodel.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for StringVerifier methods, that allows engine to find classes with custom methods by given name
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface StringVerifierName {

    /**
     * @return the name of string verifier method
     */
    String value();
}
