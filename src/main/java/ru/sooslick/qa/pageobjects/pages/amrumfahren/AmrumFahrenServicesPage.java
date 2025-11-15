package ru.sooslick.qa.pageobjects.pages.amrumfahren;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ComponentLocator;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.PageName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.components.Component;
import ru.sooslick.qa.pagemodel.element.HtmlElement;
import ru.sooslick.qa.pagemodel.element.ImageElement;

@PageName("Amrum A nach B - Unsere Dienstleistungen")
public class AmrumFahrenServicesPage extends AbstractAmrumFahrenPage {

    @ElementName("Services Header")
    @FindBy(xpath = "//h2")
    @Required
    public HtmlElement servicesHeader;

    @ElementName("Insel block")
    @FindBy(xpath = "//div[@data-zone-type='content'][2]")
    @Required
    public ServiceBlock islandServicesBlock;

    @ElementName("Festland block")
    @FindBy(xpath = "//div[@data-zone-type='content'][3]")
    @Required
    public ServiceBlock festlandBlock;

    public static class ServiceBlock extends HtmlElement {
        @ElementName("Services list")
        @FindBy(xpath = ".//*[contains(@class, 'grid-column-root')][.//h1]")
        @ComponentLocator(
                component = Component.LIST_ITEM,
                locator = @FindBy(xpath = ".//*[@class='module-container-custom module-container-root']"),
                type = ServiceItem.class
        )
        public HtmlElement inselBlock;

        @ElementName("Background")
        @FindBy(xpath = ".//child::*[1]")
        public HtmlElement background;

        @ElementName("Image")
        @FindBy(xpath = ".//img")
        public ImageElement image;
    }

    public static class ServiceItem extends HtmlElement {
        @ElementName("Block Header")
        @FindBy(xpath = ".//*[self::h1 or self::p][1]")
        public HtmlElement header;

        @ElementName("Content")
        @FindBy(xpath = "./div")
        public HtmlElement content;
    }
}
