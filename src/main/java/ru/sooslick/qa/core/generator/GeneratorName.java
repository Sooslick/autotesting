package ru.sooslick.qa.core.generator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface GeneratorName {
    String value();
}
