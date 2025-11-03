package ru.sooslick.qa.pageobjects.pages.amrumfahren;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ComponentLocator;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.PageName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.components.Component;
import ru.sooslick.qa.pagemodel.components.DefaultLiComponent;
import ru.sooslick.qa.pagemodel.element.HtmlElement;
import ru.sooslick.qa.pagemodel.element.ImageElement;

@PageName("Amrum A nach B - startseite")
public class AmrumFahrenStartseite extends AbstractAmrumFahrenPage {

    @ElementName("Main block")
    @FindBy(xpath = "//h3")
    @ComponentLocator(
            component = Component.LIST_ITEM,
            locator = @FindBy(xpath = ".//span"),
            type = DefaultLiComponent.class         // TODO - should loaded by default, but actually not! prob engine bug
    )
    @Required
    public HtmlElement mainBlock;

    @ElementName("Welcome block")
    @FindBy(xpath = "//div[@data-zone-type='content'][2]")
    @Required
    public WelcomeBlock welcomeBlock;

    @ElementName("Services block")
    @FindBy(xpath = "//div[@data-zone-type='content'][3]")
    @Required
    public ServicesBlock servicesBlock;

    public static class WelcomeBlock extends HtmlElement {
        @ElementName("Welcome header")
        @FindBy(xpath = ".//h1")
        public HtmlElement blockHeader;

        @ElementName("Company subheader")
        @FindBy(xpath = ".//h6")
        public HtmlElement blockSubHeader;

        @ElementName("Description lines")
        @FindBy(css = ".text-root")
        @ComponentLocator(
                component = Component.LIST_ITEM,
                locator = @FindBy(xpath = ".//p"),
                type = DefaultLiComponent.class         // TODO - should loaded by default, but actually not! prob engine bug
        )
        public HtmlElement textLines;

        @ElementName("Company logo")
        @FindBy(xpath = ".//img")
        public ImageElement logo;

        @ElementName("Background")
        @FindBy(xpath = ".//child::*[1]")
        public HtmlElement background;
    }

    public static class ServicesBlock extends HtmlElement {
        @ElementName("Services header")
        @FindBy(xpath = ".//h1")
        public HtmlElement blockHeader;

        @ElementName("Offer link")
        @FindBy(xpath = ".//a")
        public HtmlElement offerLink;

        @ElementName("Services list")
        @FindBy(xpath = "(.//*[contains(@class, 'module-container-root')])[3]")
        @ComponentLocator(
                component = Component.LIST_ITEM,
                locator = @FindBy(xpath = ".//p"),
                type = DefaultLiComponent.class         // TODO - should loaded by default, but actually not! prob engine bug
        )
        public HtmlElement servicesList;

        @ElementName("Left image")
        @FindBy(xpath = "(.//img)[1]")
        public ImageElement image1;

        @ElementName("Right image")
        @FindBy(xpath = "(.//img)[2]")
        public ImageElement image2;

        @ElementName("Background")
        @FindBy(xpath = ".//child::*[1]")
        public HtmlElement background;
    }
}
