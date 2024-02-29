package ru.sooslick.qa.pagemodel.actions;

import ru.sooslick.qa.pagemodel.element.HtmlElement;

public class DefaultClickAction implements ActionPerformer<Void> {
    @Override
    public Void perform(HtmlElement element) {
        element.click();
        return null;
    }
}
