package ru.sooslick.qa.pageobjects.pages;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ComponentLocator;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.PageName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.components.Component;
import ru.sooslick.qa.pagemodel.element.HtmlElement;
import ru.sooslick.qa.pagemodel.page.AbstractPage;
import ru.sooslick.qa.pageobjects.blocks.MainPageFooterBlock;

@PageName("Bingo Main page")
public class BingoMainPage extends AbstractPage {

    @ElementName("Main banner Image")
    @FindBy(id = "header-main")
    @Required
    public HtmlElement mainBannerImage;

    @ElementName("Main header Text")
    @FindBy(id = "header-main-text")
    @Required
    public HtmlElement mainBannerText;

    @ElementName("App by Text")
    @FindBy(id = "header-sub-text")
    @Required
    public HtmlElement underBannerText;

    @ElementName("Sooslick Art home button")
    @FindBy(id = "home-button")
    @Required
    public HtmlElement homeButton;

    @ElementName("Localization Drop down")
    @FindBy(id = "locale-select")
    @Required
    public HtmlElement localeDropDown;

    @ElementName("Color theme Switch")
    @FindBy(id = "theme-switch")
    @Required
    public HtmlElement colorThemeSwitch;

    @ElementName("Templates Tab")
    @FindBy(id = "template-tab-header")
    @Required
    public HtmlElement templatesTab;

    @ElementName("Tickets Tab")
    @FindBy(id = "ticket-tab-header")
    @Required
    public HtmlElement ticketsTab;

    @ElementName("Templates List")
    @FindBy(id = "template-select")
    @ComponentLocator(
            component = Component.LIST_ITEM,
            locator = @FindBy(xpath = "./div")
    )
    @Required
    public HtmlElement templateList;

    @ElementName("Tickets List")
    @FindBy(id = "ticket-select")
    @ComponentLocator(
            component = Component.LIST_ITEM,
            locator = @FindBy(xpath = "./div")
    )
    public HtmlElement ticketList;

    @ElementName("Template preview Block")
    @FindBy(id = "template-body")
    @Required
    public TemplateBlock templateBlock;

    @ElementName("Ticket preview Block")
    @FindBy(id = "ticket-body")
    public TicketBlock ticketBlock;

    @ElementName("Footer Block")
    @FindBy(id = "ft")
    @Required
    public MainPageFooterBlock footerBlock;

    public static class TemplateBlock {
        @ElementName("Preview Image")
        @FindBy(id = "template-view")
        public HtmlElement templateImage;

        @ElementName("Template name Text")
        @FindBy(id = "template-name")
        public HtmlElement templateNameText;

        @ElementName("Template author Text")
        @FindBy(id = "template-author")
        public HtmlElement templateAuthorText;

        @ElementName("Template description Text")
        @FindBy(id = "template-desc")
        public HtmlElement templateDescriptionText;

        @ElementName("Entries amount Text")
        @FindBy(id = "template-entries")
        public HtmlElement templateEntriesText;

        @ElementName("Create ticket Button")
        @FindBy(id = "template-create")
        public HtmlElement createButton;
    }

    public static class TicketBlock {
        @ElementName("Preview Image")
        @FindBy(id = "ticket-view")
        public HtmlElement ticketImage;

        @ElementName("Template name Text")
        @FindBy(id = "ticket-template")
        public HtmlElement ticketTemplateText;

        @ElementName("Ticket owner Text")
        @FindBy(id = "ticket-owner")
        public HtmlElement ticketOwnerText;

        @ElementName("Ticket date Text")
        @FindBy(id = "ticket-date")
        public HtmlElement ticketDateText;

        @ElementName("Sealed ticket Text")
        @FindBy(id = "ticket-sealed")
        public HtmlElement ticketSealedText;

        @ElementName("Created by Text")
        @FindBy(id = "ticket-create-by")
        public HtmlElement createdByText;

        @ElementName("Edit ticket Button")
        @FindBy(id = "ticket-edit")
        public HtmlElement ticketEditButton;

        @ElementName("View ticket Button")
        @FindBy(id = "ticket-view")
        public HtmlElement ticketViewButton;
    }
}
