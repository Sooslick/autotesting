package ru.sooslick.qa.pageobjects.misc;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

public class WrappedHtmlElement extends HtmlElement {
    @ElementName("Parent element")
    @FindBy(xpath = "./parent::*")
    public HtmlElement wrapperElement;
}
