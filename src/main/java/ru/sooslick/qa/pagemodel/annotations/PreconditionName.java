package ru.sooslick.qa.pagemodel.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface PreconditionName {
    String value();
}
