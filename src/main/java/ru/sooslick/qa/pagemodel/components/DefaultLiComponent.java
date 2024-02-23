package ru.sooslick.qa.pagemodel.components;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

public class DefaultLiComponent extends HtmlElement {

    @ElementName("List Item")
    @FindBy(xpath = "./.")
    public HtmlElement item;
}
