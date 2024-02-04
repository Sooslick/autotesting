package ru.sooslick.qa.pagemodel.actions;

import org.junit.jupiter.api.Assertions;
import ru.sooslick.qa.pagemodel.HtmlElement;

public class DefaultVisibilityCheckAction implements ActionPerformer {
    @Override
    public void perform(HtmlElement element) {
        Assertions.assertTrue(element.isDisplayed(), "Element '" + element.getName() + "' is not visible");
    }
}
