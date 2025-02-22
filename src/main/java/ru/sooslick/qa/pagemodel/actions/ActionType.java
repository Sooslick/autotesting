package ru.sooslick.qa.pagemodel.actions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.sooslick.qa.core.helper.ActionsHelper;

/**
 * Enumeration of customizable actions with web pages and elements.
 */
@AllArgsConstructor
@Getter
public enum ActionType {
    CLEAR(DefaultClearAction.class),
    CLICK(DefaultClickAction.class),
    CLICK_AND_HOLD(DefaultClickAndHoldAction.class),
    GET_CHECKBOX_STATE(DefaultGetCheckboxStateAction.class),
    GET_ELEMENT_VISIBILITY(DefaultGetElementVisibilityAction.class),
    GET_TEXT(DefaultGetTextAction.class),
    MOUSE_OVER(DefaultMouseOverAction.class),
    SCROLL_TO_ELEMENT(DefaultScrollToElementAction.class),
    SCROLL_TO_TOP(DefaultScrollToTopAction.class);

    private final Class<? extends ActionPerformer<?>> defaultPerformerType;

    /**
     * @return default implementation for this action.
     */
    public ActionPerformer<?> getDefaultPerformerImpl() {
        return ActionsHelper.getPerformer(defaultPerformerType);
    }
}
