package ru.sooslick.qa.pageobjects.pages;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.element.HtmlElement;
import ru.sooslick.qa.pagemodel.page.AbstractPage;
import ru.sooslick.qa.pageobjects.blocks.MainPageFooterBlock;
import ru.sooslick.qa.pageobjects.blocks.MainPageLinksBlock;

public abstract class SooslickArtAbstractPage extends AbstractPage {

    @ElementName("Sooslick Art banner Image")
    @FindBy(id = "header-img")
    @Required
    public HtmlElement sooslickArtBannerImage;

    @ElementName("Main page links Block")
    @FindBy(id = "main-panel")
    @Required
    public MainPageLinksBlock mainPageLinksBlock;

    @ElementName("Footer Block")
    @FindBy(id = "ft")
    @Required
    public MainPageFooterBlock footerBlock;
}
