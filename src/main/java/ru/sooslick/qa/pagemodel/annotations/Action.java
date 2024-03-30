package ru.sooslick.qa.pagemodel.annotations;

import ru.sooslick.qa.pagemodel.actions.ActionPerformer;
import ru.sooslick.qa.pagemodel.actions.ActionType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * HtmlElement annotation for specifying custom actions for HtmlElements.
 */
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Actions.class)
@Target(ElementType.FIELD)
public @interface Action {

    /**
     * @return Type of action.
     */
    ActionType type();

    /**
     * @return Java class that responsible for performing action of specified type.
     */
    Class<? extends ActionPerformer<?>> performer();
}
