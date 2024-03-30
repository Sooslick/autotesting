package ru.sooslick.qa.pagemodel.actions;

import ru.sooslick.qa.pagemodel.element.HtmlElement;

/**
 * Interface for various user actions on web pages and its elements.
 *
 * @param <ReturnType> Java type for values returned as result of performed action.
 */
public interface ActionPerformer<ReturnType> {

    /**
     * Performs the action and returns its result.
     *
     * @param element target element for action.
     * @return result of performed action.
     */
    ReturnType perform(HtmlElement element);
}
