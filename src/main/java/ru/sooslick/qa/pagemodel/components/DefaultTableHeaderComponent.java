package ru.sooslick.qa.pagemodel.components;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

/**
 * Simple implementation for table header component.
 * Component declares just one element, referring to component itself.
 */
public class DefaultTableHeaderComponent extends HtmlElement {

    @ElementName("Table header")
    @FindBy(xpath = "./.")
    public HtmlElement tableHeader;
}
