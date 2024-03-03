package ru.sooslick.qa.pageobjects.pages;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ComponentLocator;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.PageName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.components.Component;
import ru.sooslick.qa.pagemodel.element.HtmlElement;
import ru.sooslick.qa.pageobjects.components.PortfolioProjectListItem;

@PageName("Sooslick.Art Project - Portfolio page")
public class SooslickArtPortfolioPage extends SooslickArtAbstractPage {

    @ElementName("Showcase Header")
    @FindBy(xpath = "//h1")
    @Required
    public HtmlElement showcaseHeader;

    @ElementName("About me Text Block")
    @FindBy(id = "about-main")
    @Required
    public HtmlElement aboutMeBlock;

    @ElementName("Github Text Block")
    @FindBy(id = "about-github")
    @Required
    public HtmlElement githubBlock;

    @ElementName("Github Link")
    @FindBy(id = "about-github-link")
    @Required
    public HtmlElement githubLink;

    @ElementName("Showcase projects List")
    @FindBy(id = "portfolio-project-list")
    @ComponentLocator(
            component = Component.LI_ELEMENT,
            locator = @FindBy(xpath = "./div"),
            type = PortfolioProjectListItem.class
    )
    @Required
    public HtmlElement showcaseList;

    @ElementName("More projects Text Block")
    @FindBy(id = "more-projects-block")
    @Required
    public HtmlElement moreProjectsTextBlock;

    @ElementName("All projects Link")
    @FindBy(id = "all-projects-link")
    @Required
    public HtmlElement projectsLink;

    @ElementName("Email Link")
    @FindBy(id = "mailto-link")
    @Required
    public HtmlElement emailLink;
}
