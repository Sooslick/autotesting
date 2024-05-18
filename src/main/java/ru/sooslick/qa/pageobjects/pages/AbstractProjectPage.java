package ru.sooslick.qa.pageobjects.pages;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.element.HtmlElement;
import ru.sooslick.qa.pagemodel.page.AbstractPage;
import ru.sooslick.qa.pageobjects.blocks.MainPageFooterBlock;

public abstract class AbstractProjectPage extends AbstractPage {

    @ElementName("Home Button")
    @FindBy(xpath = "//*[@class='home-button']")
    @Required
    public HtmlElement homeButton;

    @ElementName("Footer Block")
    @FindBy(id = "ft")
    @Required
    public MainPageFooterBlock footerBlock;
}
