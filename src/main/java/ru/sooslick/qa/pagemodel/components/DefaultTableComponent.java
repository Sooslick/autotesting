package ru.sooslick.qa.pagemodel.components;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

/**
 * Simple implementation for table body component.
 * Component declares just one element, referring to component itself.
 */
public class DefaultTableComponent extends HtmlElement {

    @ElementName("Table body")
    @FindBy(xpath = "./.")
    public HtmlElement tableBody;
}
