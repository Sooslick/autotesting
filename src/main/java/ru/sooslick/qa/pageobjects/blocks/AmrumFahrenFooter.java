package ru.sooslick.qa.pageobjects.blocks;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ComponentLocator;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.components.Component;
import ru.sooslick.qa.pagemodel.element.HtmlElement;
import ru.sooslick.qa.pageobjects.components.AmrumFahrenNavigation;

public class AmrumFahrenFooter extends HtmlElement {
    @ElementName("Navigation panel")
    @FindBy(xpath = ".//*[@aria-label='Fußzeilen-Navigation']")
    @ComponentLocator(
            component = Component.LIST_ITEM,
            locator = @FindBy(xpath = ".//*[@class='navigation-menu-item-wrapper']"),
            type = AmrumFahrenNavigation.class
    )
    @Required
    public HtmlElement footerNavigation;

    @ElementName("Copyright text")
    @FindBy(xpath = ".//p[1]")
    @Required
    public HtmlElement copyright;

    @ElementName("Datenschutzerklärung link")
    @FindBy(xpath = ".//p/a")
    @Required
    public HtmlElement legal;

    @ElementName("Background")
    @FindBy(xpath = "./div")
    @Required
    public HtmlElement footerBackground;

    @ElementName("Clipped area")
    @FindBy(css = ".section-inner")
    public HtmlElement headerClippedArea;
}
