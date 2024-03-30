package ru.sooslick.qa.pagemodel.annotations;

import ru.sooslick.qa.core.StepsFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for dependency injection during instantiating Steps classes via {@link StepsFactory}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
// todo improve context injectors to make them more flexible
public @interface Context {
}
