package ru.sooslick.qa.pageobjects.pages.amrumfahren;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ComponentLocator;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.components.Component;
import ru.sooslick.qa.pagemodel.page.AbstractPage;
import ru.sooslick.qa.pageobjects.blocks.AmrumFahrenFooter;
import ru.sooslick.qa.pageobjects.blocks.AmrumFahrenLayout;
import ru.sooslick.qa.pageobjects.components.AmrumFahrenNavigation;
import ru.sooslick.qa.pageobjects.misc.WrappedHtmlElement;

abstract class AbstractAmrumFahrenPage extends AbstractPage {

    @ElementName("Header Navigation panel")
    @FindBy(xpath = "//*[@aria-label='Kopfzeilen-Navigation']")
    @ComponentLocator(
            component = Component.LIST_ITEM,
            locator = @FindBy(xpath = ".//*[@class='navigation-menu-item-wrapper']"),
            type = AmrumFahrenNavigation.class
    )
    @Required
    public WrappedHtmlElement headerNavigation;

    @ElementName("Page content")
    @FindBy(xpath = "//main")
    public AmrumFahrenLayout main;

    @ElementName("Page footer")
    @FindBy(xpath = "//footer")
    public AmrumFahrenFooter footer;
}
