package ru.sooslick.qa.pagemodel.annotations;

import ru.sooslick.qa.pagemodel.ActionType;
import ru.sooslick.qa.pagemodel.actions.ActionPerformer;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Actions.class)
public @interface Action {

    ActionType type();

    Class<? extends ActionPerformer> performer();
}
