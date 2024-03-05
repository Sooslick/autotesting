package ru.sooslick.qa.pageobjects.pages;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.PageName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

@PageName("Sooslick.Art Project - Error page")
public class SooslickArtErrorPage extends SooslickArtAbstractPage {

    @ElementName("Error Header")
    @FindBy(xpath = "//h1")
    @Required
    public HtmlElement errorHeader;

    @ElementName("Description Header")
    @FindBy(xpath = "//h2")
    @Required
    public HtmlElement subHeader;
}
