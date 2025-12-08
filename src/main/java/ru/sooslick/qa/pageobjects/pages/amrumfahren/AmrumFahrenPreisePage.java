package ru.sooslick.qa.pageobjects.pages.amrumfahren;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ComponentLocator;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.PageName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.components.Component;
import ru.sooslick.qa.pagemodel.components.DefaultLiComponent;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

@PageName("Amrum A nach B - Unsere Preise")
public class AmrumFahrenPreisePage extends AbstractAmrumFahrenPage {

    @ElementName("Unsere Preise Header")
    @FindBy(xpath = "//*[text()='Unsere Preise']")
    @Required
    public HtmlElement pageHeader;

    @ElementName("Unsere Preise Subheader")
    @FindBy(xpath = "//*[text()='Transparente Preisgestaltung ist uns sehr wichtig!']")
    @Required
    public HtmlElement pageSubheader;

    @ElementName("Feste Preise Header")
    @FindBy(xpath = "//div[@data-zone-type='content'][2]//h2")
    @Required
    public HtmlElement preiseBlockHeader;

    @ElementName("Feste Preise text lines")
    @FindBy(xpath = "(//div[@data-zone-type='content'][2]//*[@class='module-container-custom module-container-root'])[2]")
    @ComponentLocator(
            component = Component.LIST_ITEM,
            locator = @FindBy(xpath = ".//p"),
            type = DefaultLiComponent.class
    )
    @Required
    public HtmlElement preiseBlockText;

    @ElementName("Destinations List")
    @FindBy(css = "main")
    @ComponentLocator(
            component = Component.LIST_ITEM,
            locator = @FindBy(css = ".grid-row-root.grid-row-has-columns .grid-column-medium-4.grid-column-large-4"),
            type = DestinationBlock.class
    )
    @Required
    public HtmlElement destinationsList;

    public static class DestinationBlock extends HtmlElement {
        @ElementName("Block Image")
        @FindBy(xpath = ".//img")
        public HtmlElement blockImage;

        @ElementName("Block Header")
        @FindBy(xpath = ".//h3")
        public HtmlElement blockHeader;

        @ElementName("Text lines")
        @FindBy(xpath = ".//*[contains(@class, 'text-root')]")
        public HtmlElement textLines;
    }
}
