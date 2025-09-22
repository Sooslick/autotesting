package ru.sooslick.qa.pageobjects.pages.amrumfahren;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ComponentLocator;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.components.Component;
import ru.sooslick.qa.pagemodel.element.HtmlElement;
import ru.sooslick.qa.pagemodel.page.AbstractPage;
import ru.sooslick.qa.pageobjects.components.AmrumFahrenNavigation;

abstract class AbstractAmrumFahrenPage extends AbstractPage {

    @ElementName("Header Navigation panel")
    @FindBy(xpath = "//*[@aria-label='Kopfzeilen-Navigation']")
    @ComponentLocator(
            component = Component.LIST_ITEM,
            locator = @FindBy(xpath = ".//*[@class='navigation-menu-item-wrapper']"),
            type = AmrumFahrenNavigation.class
    )
    @Required
    public HtmlElement headerNavigation;

    @ElementName("Footer Navigation panel")
    @FindBy(xpath = "//*[@aria-label='Fußzeilen-Navigation']")
    @ComponentLocator(
            component = Component.LIST_ITEM,
            locator = @FindBy(xpath = ".//*[@class='navigation-menu-item-wrapper']"),
            type = AmrumFahrenNavigation.class
    )
    @Required
    public HtmlElement footerNavigation;

    @ElementName("Copyright text")
    @FindBy(xpath = "//footer//p[1]")
    @Required
    public HtmlElement copyright;

    @ElementName("Datenschutzerklärung link")
    @FindBy(xpath = "//footer//p/a")
    @Required
    public HtmlElement legal;
}
