package ru.sooslick.qa.pagemodel.actions;

import ru.sooslick.qa.pagemodel.HtmlElement;

public class DefaultGetTextAction implements ActionPerformer<String> {

    @Override
    public String perform(HtmlElement element) {
        return element.getText();
    }
}
