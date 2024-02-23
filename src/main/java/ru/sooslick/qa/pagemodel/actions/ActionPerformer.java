package ru.sooslick.qa.pagemodel.actions;

import ru.sooslick.qa.pagemodel.element.HtmlElement;

public interface ActionPerformer<ReturnType> {

    ReturnType perform(HtmlElement element);
}
