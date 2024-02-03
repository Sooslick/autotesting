package ru.sooslick.qa.pages;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.Component;
import ru.sooslick.qa.pagemodel.HtmlElement;
import ru.sooslick.qa.pagemodel.annotations.ComponentLocator;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.Required;

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
    @ComponentLocator(component = Component.LI_ELEMENT, findBy = @FindBy(xpath = "./a"))
    @Required
    public HtmlElement socialsImageList;
    // todo list type

    @ElementName("Copyrights Text")
    @FindBy(xpath = "./*[@id='copyrights']")
    @Required
    public HtmlElement copyrightText;
}
