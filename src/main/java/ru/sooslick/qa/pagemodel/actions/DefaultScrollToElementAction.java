package ru.sooslick.qa.pagemodel.actions;

import org.openqa.selenium.interactions.Actions;
import ru.sooslick.qa.pagemodel.HtmlElement;

public class DefaultScrollToElementAction implements ActionPerformer {
    @Override
    public void perform(HtmlElement element) {
        // todo this thing able to scroll to cached element but unable to do so with source element!
        new Actions(element.getWrappedDriver())
                .scrollToElement(element.getCachedElement())
                .build()
                .perform();
    }
}
