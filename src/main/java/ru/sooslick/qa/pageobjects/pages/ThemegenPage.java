package ru.sooslick.qa.pageobjects.pages;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.PageName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.element.HtmlElement;
import ru.sooslick.qa.pagemodel.element.ImageElement;

@PageName("Theme Randomizer main page")
public class ThemegenPage extends AbstractProjectPage {

    @ElementName("Page Header")
    @FindBy(xpath = "//*[@class='header-content']")
    @Required
    public HtmlElement pageHeader;

    @ElementName("Theme Annotation text")
    @FindBy(xpath = "//*[@class='text-pad']")
    @Required
    public HtmlElement annotationText;

    @ElementName("Theme text")
    @FindBy(xpath = "//*[@class='rand-result']")
    public HtmlElement themeText;

    @ElementName("Reload Icon")
    @FindBy(css = ".refresh-result img")
    public ImageElement reloadIcon;

    @ElementName("About Block")
    @FindBy(xpath = "//*[@class='text-about']")
    @Required
    public HtmlElement aboutBlock;

    @ElementName("About Header")
    @FindBy(xpath = "//*[@class='text-about']//h1")
    @Required
    public HtmlElement aboutHeader;

    @ElementName("Special Page Link")
    @FindBy(xpath = "//a[contains(text(), 'специальная страница')]")
    @Required
    public HtmlElement specialPageLink;
}
