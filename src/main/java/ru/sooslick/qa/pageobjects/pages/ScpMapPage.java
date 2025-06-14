package ru.sooslick.qa.pageobjects.pages;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.actions.ActionType;
import ru.sooslick.qa.pagemodel.actions.CtrlABackspaceClearAction;
import ru.sooslick.qa.pagemodel.actions.IsElementInViewportCheck;
import ru.sooslick.qa.pagemodel.annotations.Action;
import ru.sooslick.qa.pagemodel.annotations.ComponentLocator;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.PageName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.components.Component;
import ru.sooslick.qa.pagemodel.components.DefaultLiComponent;
import ru.sooslick.qa.pagemodel.element.HtmlElement;
import ru.sooslick.qa.pagemodel.element.ImageElement;
import ru.sooslick.qa.pagemodel.element.TableElement;
import ru.sooslick.qa.pagemodel.page.AbstractPage;

@PageName("SCP: Containment Breach map")
public class ScpMapPage extends AbstractPage {

    @ElementName("Seed Block")
    @FindBy(id = "inputs")
    @Required
    public SeedBlock seedBlock;

    @ElementName("App Info Block")
    @FindBy(id = "page-meta")
    @ComponentLocator(
            component = Component.LIST_ITEM,
            locator = @FindBy(xpath = "./div"),
            type = DefaultLiComponent.class
    )
    @Required
    public AppInfoBlock appInfoBlock;

    @ElementName("Map Block")
    @FindBy(id = "map-block")
    public ScpMapBlock mapBlock;

    public static class SeedBlock extends HtmlElement {

        @ElementName("Vanilla seed input")
        @FindBy(id = "prompt")
        @Action(type = ActionType.CLEAR, performer = CtrlABackspaceClearAction.class)
        public HtmlElement vanillaSeedInput;

        @ElementName("Vanilla seed text")
        @FindBy(id = "prompt-label")
        public HtmlElement vanillaSeedText;

        @ElementName("Vanilla seed hint")
        @FindBy(id = "prompt-hint")
        public HtmlElement vanillaSeedHint;

        @ElementName("Modded seed input")
        @FindBy(id = "seed")
        @Action(type = ActionType.CLEAR, performer = CtrlABackspaceClearAction.class)
        public HtmlElement moddedSeedInput;

        @ElementName("Modded seed text")
        @FindBy(id = "seed-label")
        public HtmlElement moddedSeedText;

        @ElementName("Modded seed hint")
        @FindBy(id = "seed-hint")
        public HtmlElement moddedSeedHint;

        @ElementName("Blank fields hint")
        @FindBy(id = "blank-hint")
        public HtmlElement blankFieldHint;

        @ElementName("Create Map button")
        @FindBy(id = "generate")
        public HtmlElement createMapButton;

        @ElementName("Loading icon")
        @FindBy(id = "loading-gif")
        public ImageElement loadingIcon;
    }

    public static class AppInfoBlock extends HtmlElement {

        @ElementName("Sooslick link")
        @FindBy(id = "sooslick-link")
        public HtmlElement sooslickLink;

        @ElementName("Download game link")
        @FindBy(id = "scpcbgame-link")
        public HtmlElement gameLink;

        @ElementName("Download mod link")
        @FindBy(id = "mod-link")
        public HtmlElement modLink;
    }

    public static class ScpMapBlock extends HtmlElement {

        @ElementName("Seed map")
        @FindBy(id = "map")
        @ComponentLocator(
                component = Component.TABLE_BODY,
                locator = @FindBy(xpath = ".//table"))
        @ComponentLocator(
                component = Component.TABLE_BODY_ROW,
                locator = @FindBy(xpath = ".//tr"))
        public TableElement seedMap;

        @ElementName("Map Metadata block")
        @FindBy(id = "map-info")
        public MapMetadataBlock mapMetaBlock;

        @ElementName("Room Info block")
        @FindBy(id = "room-info")
        public RoomInfoBlock roomInfoBlock;

        @ElementName("Pocket Dimension map")
        @FindBy(id = "map-pd")
        public HtmlElement pdMap;

        @ElementName("Forest map")
        @FindBy(id = "map-forest")
        public HtmlElement forestMap;

        @ElementName("Share button")
        @FindBy(id = "map-share")
        @Action(type = ActionType.GET_ELEMENT_VISIBILITY, performer = IsElementInViewportCheck.class)
        public HtmlElement reportButton;

        @ElementName("Copied to clipboard Text")
        @FindBy(id = "copied-text")
        public HtmlElement reportHint;

        // TODO: I have no nice solution for grid yet, so I've added some pre-defined cells

        @ElementName("Cell 8,16")
        @FindBy(id = "c8-16")
        public MapCell cell8x16;

        @ElementName("Cell 15,15")
        @FindBy(id = "c15-15")
        public MapCell cell15x15;

        @ElementName("Cell 5,17")
        @FindBy(id = "c5-17")
        public MapCell cell5x17;

        @ElementName("Cell 5,15")
        @FindBy(id = "c5-15")
        public MapCell cell5x15;

        @ElementName("Cell 13,8")
        @FindBy(id = "c13-8")
        public MapCell cell13x8;
    }

    public static class MapCell extends HtmlElement {
        @ElementName("Room image")
        @FindBy(xpath = ".//*[local-name() = 'svg']")
        public ImageElement cellSvg;

        @ElementName("Text label")
        @FindBy(xpath = ".//div")
        public ImageElement cellLabel;
    }

    public static class MapMetadataBlock extends HtmlElement {

        @ElementName("Map metadata header")
        @FindBy(xpath = ".//*[@class='meta-head']")
        public HtmlElement blockHeader;

        @ElementName("Seed String text")
        @FindBy(id = "seedString-container")
        public HtmlElement seedString;

        @ElementName("Seed Number text")
        @FindBy(id = "seedValue-container")
        public HtmlElement seednumber;

        @ElementName("Loading Screen text")
        @FindBy(id = "loadingScreen-container")
        public HtmlElement loadingScreen;

        @ElementName("SCP-106 timer text")
        @FindBy(id = "state106-container")
        public HtmlElement scp106timer;

        @ElementName("SCP-106 timer hint")
        @FindBy(id = "state106-hint")
        public HtmlElement scp106timerHint;

        @ElementName("SCP-106 timer popup")
        @FindBy(xpath = ".//*[@id='state106-hint']/*")
        public HtmlElement scp106timerPopup;

        @ElementName("Initial Angle text")
        @FindBy(id = "statePlayer-container")
        public HtmlElement initialAngle;

        @ElementName("Initial Angle hint")
        @FindBy(id = "statePlayer-hint")
        public HtmlElement initialAngleHint;

        @ElementName("Initial Angle popup")
        @FindBy(xpath = ".//*[@id='statePlayer-hint']/*")
        public HtmlElement initialAnglePopup;
    }

    public static class RoomInfoBlock extends HtmlElement {
        @ElementName("Room Info header")
        @FindBy(xpath = ".//*[@class='meta-head']")
        public HtmlElement blockHeader;

        @ElementName("Room text")
        @FindBy(id = "room-container")
        public HtmlElement roomText;

        @ElementName("Events text")
        @FindBy(id = "room-event-container")
        public HtmlElement eventText;

        @ElementName("Extra Info text")
        @FindBy(id = "rnd-info-container")
        public HtmlElement extraInfoText;
    }

}
