package ru.sooslick.qa.pagemodel.actions;

import org.junit.jupiter.api.Assertions;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

public class DefaultCheckElementVisibleAction implements ActionPerformer<Void> {
    @Override
    public Void perform(HtmlElement element) {
        Assertions.assertTrue(element.isDisplayed(), "Element '" + element.getName() + "' is not visible");
        return null;
    }
}
