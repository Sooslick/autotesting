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
    @FindBy(xpath = "//*[contains(text(), 'Auf der Insel')]/ancestor::*[@class='section-content']")
    @ComponentLocator(
            component = Component.LIST_ITEM,
            locator = @FindBy(xpath = ".//*[@class='module-container-custom module-container-root']"),
            type = ServiceBlock.class
    )
    @Required
    public ServiceBlock inselBlock;

    @ElementName("Festland block")
    @FindBy(xpath = "//*[contains(text(), 'oder auf dem Festland')]/ancestor::*[@class='section-content']")
    @ComponentLocator(
            component = Component.LIST_ITEM,
            locator = @FindBy(xpath = ".//*[@class='module-container-custom module-container-root']"),
            type = ServiceBlock.class
    )
    @Required
    public ServiceBlock festlandBlock;

    public static class ServiceBlock extends HtmlElement {
        @ElementName("Block Header")
        @FindBy(xpath = ".//p[1]")
        public HtmlElement header;

        @ElementName("Content")
        @FindBy(xpath = "./div")
        public HtmlElement content;

        @ElementName("Image")
        @FindBy(xpath = ".//img")
        public ImageElement image;
    }
}
