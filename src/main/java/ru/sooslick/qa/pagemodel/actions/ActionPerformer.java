package ru.sooslick.qa.pagemodel.actions;

import ru.sooslick.qa.pagemodel.HtmlElement;

public interface ActionPerformer<ReturnType> {

    ReturnType perform(HtmlElement element);
}
