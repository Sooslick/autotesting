package ru.sooslick.qa.pagemodel.annotations;

import ru.sooslick.qa.pagemodel.actions.ActionPerformer;
import ru.sooslick.qa.pagemodel.actions.ActionType;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Actions.class)
public @interface Action {

    ActionType type();

    Class<? extends ActionPerformer<?>> performer();
}
