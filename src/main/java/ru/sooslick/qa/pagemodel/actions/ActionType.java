package ru.sooslick.qa.pagemodel.actions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.sooslick.qa.core.helper.ActionsHelper;

/**
 * Enumeration of customizable actions with web pages and elements.
 */
@AllArgsConstructor
@Getter
// todo rework actions and add javadocs
public enum ActionType {
    CHECK_ELEMENT_VISIBLE(DefaultCheckElementVisibleAction.class),
    CLICK(DefaultClickAction.class),
    CLICK_AND_HOLD(DefaultClickAndHoldAction.class),
    GET_CHECKBOX_STATE(DefaultGetCheckboxStateAction.class),
    GET_TEXT(DefaultGetTextAction.class),
    MOUSE_OVER(DefaultMouseOverAction.class),
    SCROLL_TO_ELEMENT(DefaultScrollToElementAction.class);

    private final Class<? extends ActionPerformer<?>> defaultPerformerType;

    /**
     * @return default implementation for this action.
     */
    public ActionPerformer<?> getDefaultPerformerImpl() {
        return ActionsHelper.getPerformer(defaultPerformerType);
    }
}
