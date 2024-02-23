package ru.sooslick.qa.pagemodel.actions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.sooslick.qa.core.helper.ActionsHelper;

@AllArgsConstructor
@Getter
public enum ActionType {
    CHECK_ELEMENT_VISIBLE(DefaultCheckElementVisibleAction.class),
    GET_TEXT(DefaultGetTextAction.class),
    MOUSE_OVER(DefaultMouseOverAction.class),
    SCROLL_TO_ELEMENT(DefaultScrollToElementAction.class);

    private final Class<? extends ActionPerformer<?>> defaultPerformerType;

    public ActionPerformer<?> getDefaultPerformerImpl() {
        return ActionsHelper.getPerformer(defaultPerformerType);
    }
}
