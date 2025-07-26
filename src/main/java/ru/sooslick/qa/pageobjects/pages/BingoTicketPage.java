package ru.sooslick.qa.pageobjects.pages;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ComponentLocator;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.PageName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.components.Component;
import ru.sooslick.qa.pagemodel.element.HtmlElement;
import ru.sooslick.qa.pagemodel.page.AbstractPage;

@PageName("Bingo Ticket page")
public class BingoTicketPage extends AbstractPage {

    @ElementName("Ticket Name field")
    @FindBy(id = "ticket-name")
    @Required
    public HtmlElement ticketHeaderTextarea;

    @ElementName("Bingo grid")
    @FindBy(id = "ticket-container")
    @ComponentLocator(
            component = Component.LIST_ITEM,
            locator = @FindBy(xpath = ".//td"),
            type = BingoTicketCell.class
    )
    @Required
    public HtmlElement bingoGrid;

    @ElementName("Save button")
    @FindBy(id = "save-button")
    public HtmlElement saveButton;

    @ElementName("Save & Seal button")
    @FindBy(id = "save-seal-button")
    public HtmlElement saveSealButton;

    public static class BingoTicketCell extends HtmlElement {
        @ElementName("Item")
        @FindBy(xpath = ".//*[@class='cell-text']")
        public HtmlElement item;

        @ElementName("Cross Mark")
        @FindBy(xpath = ".//*[@class='cell-cross-mark']")
        public HtmlElement crossMark;

        @ElementName("Horizontal Strike")
        @FindBy(xpath = ".//*[@class='strike-row']")
        public HtmlElement horStrike;

        @ElementName("Vertical Strike")
        @FindBy(xpath = ".//*[@class='strike-col']")
        public HtmlElement verStrike;
    }
}
