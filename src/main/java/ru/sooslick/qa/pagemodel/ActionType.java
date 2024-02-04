package ru.sooslick.qa.pagemodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.sooslick.qa.pagemodel.actions.ActionPerformer;
import ru.sooslick.qa.pagemodel.actions.DefaultVisibilityCheckAction;

@AllArgsConstructor
@Getter
public enum ActionType {
    VISIBILITY_CHECK(DefaultVisibilityCheckAction.class);

    private final Class<? extends ActionPerformer> defaultPerformer;
}
