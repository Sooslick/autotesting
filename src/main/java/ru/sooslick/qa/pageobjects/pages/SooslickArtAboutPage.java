package ru.sooslick.qa.pageobjects.pages;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.PageName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.element.HtmlElement;
import ru.sooslick.qa.pagemodel.element.ImageElement;

@PageName("Sooslick.Art Project - About page")
public class SooslickArtAboutPage extends SooslickArtAbstractPage {

    @ElementName("What is this page Header")
    @FindBy(xpath = "//h1")
    @Required
    public HtmlElement whatIsThisHeader;

    @ElementName("About Us Text Block")
    @FindBy(id = "about-main")
    @Required
    public HtmlElement aboutUsTextBlock;

    @ElementName("Lore Banner")
    @FindBy(id = "about-banner")
    @Required
    public ImageElement loreImage;

    @ElementName("Lore Text Block")
    @FindBy(id = "about-lore")
    @Required
    public HtmlElement loreTextBlock;
}
