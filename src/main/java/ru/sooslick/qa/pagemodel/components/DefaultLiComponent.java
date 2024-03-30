package ru.sooslick.qa.pagemodel.components;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

/**
 * Simple implementation for list item component.
 * Component declares just one element, referring to component itself.
 */
public class DefaultLiComponent extends HtmlElement {

    @ElementName("List Item")
    @FindBy(xpath = "./.")
    public HtmlElement item;
}
