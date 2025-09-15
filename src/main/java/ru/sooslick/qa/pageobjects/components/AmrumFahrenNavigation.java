package ru.sooslick.qa.pageobjects.components;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

public class AmrumFahrenNavigation extends HtmlElement {
    @ElementName("Link")
    @FindBy(xpath = "./a")
    public HtmlElement link;
}
