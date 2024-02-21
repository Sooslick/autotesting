package ru.sooslick.qa.pagemodel.actions;

import org.openqa.selenium.interactions.Actions;
import ru.sooslick.qa.pagemodel.HtmlElement;

public class DefaultMouseOverAction implements ActionPerformer<Void> {
    @Override
    public Void perform(HtmlElement element) {
        // todo this thing able to interact w/ cached element but unable to do so with  HtmlElement!
        new Actions(element.getWrappedDriver())
                .moveToElement(element.getCachedElement())
                .build()
                .perform();
        return null;
    }
}
