package ru.sooslick.qa.pagemodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.sooslick.qa.pagemodel.actions.ActionPerformer;
import ru.sooslick.qa.pagemodel.actions.DefaultCheckElementVisibleAction;
import ru.sooslick.qa.pagemodel.actions.DefaultGetTextAction;
import ru.sooslick.qa.pagemodel.actions.DefaultMouseOverAction;
import ru.sooslick.qa.pagemodel.actions.DefaultScrollToElementAction;

@AllArgsConstructor
@Getter
public enum ActionType {
    CHECK_ELEMENT_VISIBLE(DefaultCheckElementVisibleAction.class),
    GET_TEXT(DefaultGetTextAction.class),
    MOUSE_OVER(DefaultMouseOverAction.class),
    SCROLL_TO_ELEMENT(DefaultScrollToElementAction.class);

    private final Class<? extends ActionPerformer<?>> defaultPerformer;
}
