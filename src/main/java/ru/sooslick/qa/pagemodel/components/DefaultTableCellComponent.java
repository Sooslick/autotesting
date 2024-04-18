package ru.sooslick.qa.pagemodel.components;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

/**
 * Simple implementation for table cell component.
 * Component declares just one element, referring to component itself.
 */
public class DefaultTableCellComponent extends HtmlElement {

    @ElementName("Cell")
    @FindBy(xpath = "./.")
    public HtmlElement tableCell;
}
