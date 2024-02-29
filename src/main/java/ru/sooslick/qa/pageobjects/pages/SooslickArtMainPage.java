package ru.sooslick.qa.pageobjects.pages;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.PageName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.element.HtmlElement;
import ru.sooslick.qa.pageobjects.blocks.ProjectLinkBlock;

@PageName("Sooslick.Art Project - Main page")
public class SooslickArtMainPage extends SooslickArtAbstractPage {

    @ElementName("Main banner Image")
    @FindBy(id = "banner-img")
    public HtmlElement mainBannerImage;

    @ElementName("About us Text Block")
    @FindBy(id = "main-page-about")
    @Required
    public HtmlElement projectAboutTextBlock;

    @ElementName("Featured project Header")
    @FindBy(xpath = "//*[@id='featuring']/h1")
    @Required
    public HtmlElement featuredProjectHeader;

    @ElementName("Featured project Link")
    @FindBy(xpath = "//*[@id='featuring']/a")
    @Required
    public ProjectLinkBlock featuredProjectLink;

    @ElementName("Other projects Link")
    @FindBy(xpath = "//*[@id='projects-all']/a")
    @Required
    public HtmlElement otherProjectsLink;
}
