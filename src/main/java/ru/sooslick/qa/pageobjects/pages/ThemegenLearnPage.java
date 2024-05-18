package ru.sooslick.qa.pageobjects.pages;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.PageName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.element.HtmlElement;
import ru.sooslick.qa.pagemodel.page.AbstractPage;

@PageName("Theme Randomizer Learn page")
public class ThemegenLearnPage extends AbstractPage {

    @ElementName("Home Link")
    @FindBy(xpath = "//*[@class='home-link']/a")
    @Required
    public HtmlElement homeLink;

    @ElementName("Rules Block")
    @FindBy(xpath = "//*[@class='rules']")
    @Required
    public HtmlElement rulesBlock;

    @ElementName("Mode Switch")
    @FindBy(xpath = "//*[@class='mode-selector']")
    @Required
    public HtmlElement modeSwitch;

    @ElementName("Mode Label")
    @FindBy(xpath = "//*[@class='mode-label']")
    @Required
    public HtmlElement modeLabel;

    @ElementName("First word Text")
    @FindBy(id = "w0")
    @Required
    public HtmlElement word1text;

    @ElementName("Second word Text")
    @FindBy(id = "w1")
    @Required
    public HtmlElement word2text;

    @ElementName("+1 Button")
    @FindBy(id = "bOk")
    @Required
    public HtmlElement okButton;

    @ElementName("0 Button")
    @FindBy(id = "bXz")
    @Required
    public HtmlElement idkButton;

    @ElementName("-1 Button")
    @FindBy(id = "bNo")
    @Required
    public HtmlElement notOkButton;

    @ElementName("Debug Text Block")
    @FindBy(xpath = "//*[@class='dbg']")
    @Required
    public HtmlElement debugBlock;
}
