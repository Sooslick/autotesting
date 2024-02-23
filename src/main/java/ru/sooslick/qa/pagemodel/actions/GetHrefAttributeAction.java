package ru.sooslick.qa.pagemodel.actions;

import ru.sooslick.qa.pagemodel.element.HtmlElement;

public class GetHrefAttributeAction implements ActionPerformer<String> {

    @Override
    public String perform(HtmlElement element) {
        return element.getAttribute("href");
    }
}
