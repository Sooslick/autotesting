package ru.sooslick.qa.pages;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.HtmlElement;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.Required;

@ElementName("Main page links Block")
public class MainPageLinksBlock extends HtmlElement {

    @ElementName("Main page Link")
    @FindBy(xpath = ".//*[@id='header-link-main']")
    @Required
    public HtmlElement mainPageLink;

    @ElementName("All projects Link")
    @FindBy(xpath = ".//*[@id='header-link-projects']")
    @Required
    public HtmlElement allProjectsLink;

    @ElementName("About us Link")
    @FindBy(xpath = ".//*[@id='header-link-about']")
    @Required
    public HtmlElement aboutUsLink;

    @ElementName("Portfolio Link")
    @FindBy(xpath = ".//*[@id='header-link-showcase']")
    @Required
    public HtmlElement portfolioLink;
}
