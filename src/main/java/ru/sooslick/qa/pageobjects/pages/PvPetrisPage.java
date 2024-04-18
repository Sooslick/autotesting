package ru.sooslick.qa.pageobjects.pages;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.PageName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.element.HtmlElement;
import ru.sooslick.qa.pagemodel.element.TableElement;
import ru.sooslick.qa.pagemodel.page.AbstractPage;
import ru.sooslick.qa.pageobjects.blocks.MainPageFooterBlock;

@PageName("PvPetris main page")
public class PvPetrisPage extends AbstractPage {

    @ElementName("Page Header")
    @FindBy(xpath = "//*[@class='header-content']")
    @Required
    public HtmlElement pageHeader;

    @ElementName("Home Button")
    @FindBy(xpath = "//*[@class='home-button']")
    @Required
    public HtmlElement homeButton;

    @ElementName("Current version Text")
    @FindBy(xpath = "//*[@class='download-header']")
    @Required
    public HtmlElement downloadCaptionText;

    @ElementName("Download Button")
    @FindBy(xpath = "//*[@class='download']/a")
    @Required
    public HtmlElement downloadButton;

    @ElementName("Download Icon")
    @FindBy(xpath = "//*[@class='download']/a/img")
    public HtmlElement downloadIcon;

    @ElementName("About Text block")
    @FindBy(xpath = "//*[@class='featuring']")
    @Required
    public HtmlElement aboutText;

    @ElementName("Github Link")
    @FindBy(xpath = "//*[@class='featuring']//*[contains(text(), 'Github link')]")
    public HtmlElement githubLink;

    @ElementName("Scores Header")
    @FindBy(xpath = "//*[@class='pvpScore']/h1")
    @Required
    public HtmlElement scoresHeader;

    @ElementName("Scores Table")
    @FindBy(xpath = "//*[@class='pvpScore']/table")
    @Required
    public TableElement scoresTable;

    @ElementName("Footer Block")
    @FindBy(id = "ft")
    @Required
    public MainPageFooterBlock footerBlock;
}
