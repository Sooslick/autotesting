package ru.sooslick.qa.pagemodel.actions;

import ru.sooslick.qa.pagemodel.element.HtmlElement;

public class DefaultGetCheckboxStateAction implements ActionPerformer<Boolean> {

    @Override
    public Boolean perform(HtmlElement element) {
        return element.getAttribute("checked") != null;
    }
}
