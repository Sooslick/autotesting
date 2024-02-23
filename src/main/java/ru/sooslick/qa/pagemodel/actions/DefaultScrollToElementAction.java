package ru.sooslick.qa.pagemodel.actions;

import org.openqa.selenium.interactions.Actions;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

public class DefaultScrollToElementAction implements ActionPerformer<Void> {
    @Override
    public Void perform(HtmlElement element) {
        // todo this thing able to scroll to cached element but unable to do so with source element!
        new Actions(element.getWrappedDriver())
                .scrollToElement(element.getCachedElement())
                .build()
                .perform();
        return null;
    }
}
