package ru.sooslick.qa.pagemodel.actions;

import ru.sooslick.qa.pagemodel.element.HtmlElement;

public class GetIdAsTextAction implements ActionPerformer<String> {

    @Override
    public String perform(HtmlElement element) {
        return element.getAttribute("id");
    }
}
