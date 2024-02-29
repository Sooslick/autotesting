package ru.sooslick.qa.pagemodel.actions;

import org.openqa.selenium.interactions.Actions;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

public class DefaultClickAndHoldAction implements ActionPerformer<Void> {
    @Override
    public Void perform(HtmlElement element) {
        // refresh if stale
        element.isDisplayed();
        new Actions(element.getWrappedDriver())
                .clickAndHold(element.getCachedElement())
                .build()
                .perform();
        return null;
    }
}
