package ru.sooslick.qa.pages;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.AbstractPage;
import ru.sooslick.qa.pagemodel.HtmlElement;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.PageName;
import ru.sooslick.qa.pagemodel.annotations.Required;

// todo abstract main page ref
@PageName("Sooslick.Art Project - Main page")
public class SooslickArtMainPage extends AbstractPage {

    @ElementName("Main banner Image")
    @FindBy(id = "banner-img")
    public HtmlElement mainBannerImage;

    @ElementName("Sooslick Art banner Image")
    @FindBy(id = "header-img")
    @Required
    public HtmlElement sooslickArtBannerImage;

    @ElementName("Main page links Block")
    @FindBy(id = "main-panel")
    @Required
    public MainPageLinksBlock mainPageLinksBlock;

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

    @ElementName("Footer Block")
    @FindBy(id = "ft")
    @Required
    public MainPageFooterBlock footerBlock;
}
