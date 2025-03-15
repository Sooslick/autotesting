package ru.sooslick.qa.pageobjects.pages;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.actions.ActionType;
import ru.sooslick.qa.pagemodel.actions.CtrlABackspaceClearAction;
import ru.sooslick.qa.pagemodel.annotations.Action;
import ru.sooslick.qa.pagemodel.annotations.ComponentLocator;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.PageName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.components.Component;
import ru.sooslick.qa.pagemodel.components.DefaultLiComponent;
import ru.sooslick.qa.pagemodel.element.HtmlElement;
import ru.sooslick.qa.pagemodel.element.ImageElement;
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
        @FindBy(xpath = ".//*[@id='prompt']/parent::*/following-sibling::*")
        public HtmlElement vanillaSeedText;

        // TODO SOOSLICK CREATE DATA-TEST-ID YOU FREAKING MONSTER
        @ElementName("Vanilla seed hint")
        @FindBy(xpath = ".//*[@class='input-hint'][1]")
        public HtmlElement vanillaSeedHint;

        @ElementName("Modded seed input")
        @FindBy(id = "seed")
        @Action(type = ActionType.CLEAR, performer = CtrlABackspaceClearAction.class)
        public HtmlElement moddedSeedInput;

        @ElementName("Modded seed text")
        @FindBy(xpath = ".//*[@id='seed']/parent::*/following-sibling::*")
        public HtmlElement moddedSeedText;

        @ElementName("Modded seed hint")
        @FindBy(xpath = ".//*[@class='input-hint'][2]")
        public HtmlElement moddedSeedHint;

        @ElementName("Blank fields hint")
        @FindBy(xpath = ".//*[@class='input-hint'][3]")
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
        @FindBy(xpath = ".//a[text() = '@Sooslick']")
        public HtmlElement sooslickLink;

        @ElementName("Download game link")
        @FindBy(xpath = ".//a[text() = 'Official website']")
        public HtmlElement gameLink;
    }

    public static class ScpMapBlock extends HtmlElement {

        @ElementName("Seed map")
        @FindBy(id = "map")
        public HtmlElement seedMap;

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

        @ElementName("Report button")
        @FindBy(id = "map-report")
        public HtmlElement reportButton;

        @ElementName("Reported map error Text")
        @FindBy(id = "reported-text")
        public HtmlElement reportHint;

        @ElementName("Cell 8,16")
        @FindBy(id = "c8-16")
        public HtmlElement cell8x16;
    }

    public static class MapMetadataBlock extends HtmlElement {

        @ElementName("Map metadata header")
        @FindBy(xpath = ".//*[@class='meta-head']")
        public HtmlElement blockHeader;

        @ElementName("Seed String text")
        @FindBy(xpath = ".//*[@id='seedString']/parent::*")
        public HtmlElement seedString;

        @ElementName("Seed Number text")
        @FindBy(xpath = ".//*[@id='seedValue']/parent::*")
        public HtmlElement seednumber;

        @ElementName("Loading Screen text")
        @FindBy(xpath = ".//*[@id='loadingScreen']/parent::*")
        public HtmlElement loadingScreen;

        @ElementName("SCP-106 timer text")
        @FindBy(xpath = ".//*[@id='state106']/parent::*")
        public HtmlElement scp106timer;

        @ElementName("SCP-106 timer hint")
        @FindBy(xpath = ".//*[@id='state106']/following-sibling::*")
        public HtmlElement scp106timerHint;

        @ElementName("SCP-106 timer popup")
        @FindBy(xpath = ".//*[@id='state106']/following-sibling::*/*")
        public HtmlElement scp106timerPopup;

        @ElementName("Initial Angle text")
        @FindBy(xpath = ".//*[@id='statePlayer']/parent::*")
        public HtmlElement initialAngle;

        @ElementName("Initial Angle hint")
        @FindBy(xpath = ".//*[@id='statePlayer']/following-sibling::*")
        public HtmlElement initialAngleHint;

        @ElementName("Initial Angle popup")
        @FindBy(xpath = ".//*[@id='statePlayer']/following-sibling::*/*")
        public HtmlElement initialAnglePopup;
    }

    public static class RoomInfoBlock extends HtmlElement {
        @ElementName("Room Info header")
        @FindBy(xpath = ".//*[@class='meta-head']")
        public HtmlElement blockHeader;

        @ElementName("Room text")
        @FindBy(xpath = ".//*[@id='room']/parent::*")
        public HtmlElement roomText;

        @ElementName("Events text")
        @FindBy(xpath = ".//*[@id='room-event']/parent::*")
        public HtmlElement eventText;

        @ElementName("Extra Info text")
        @FindBy(xpath = ".//*[@id='rnd-info']/parent::*")
        public HtmlElement extraInfoText;
    }

}
