package ru.sooslick.qa.pageobjects.blocks;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ComponentLocator;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.components.Component;
import ru.sooslick.qa.pagemodel.element.HtmlElement;
import ru.sooslick.qa.pageobjects.components.MainPageFooterItem;

@ElementName("Project link Block")
public class MainPageFooterBlock extends HtmlElement {

    @ElementName("Footer separator")
    @FindBy(xpath = "./hr")
    public HtmlElement footerSeparator;

    @ElementName("Check out our socials Text")
    @FindBy(xpath = "./*[@id='ft-checkout']")
    @Required
    public HtmlElement checkoutText;

    @ElementName("Socials Image list")
    @FindBy(xpath = "./*[@id='ft-list']")
    @ComponentLocator(
            component = Component.LIST_ITEM,
            type = MainPageFooterItem.class,
            locator = @FindBy(xpath = "./a"))
    @Required
    public HtmlElement socialsImageList;

    @ElementName("Copyrights Text")
    @FindBy(xpath = "./*[@id='copyrights']")
    @Required
    public HtmlElement copyrightText;
}
