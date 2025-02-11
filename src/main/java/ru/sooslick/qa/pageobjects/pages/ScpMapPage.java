package ru.sooslick.qa.pageobjects.pages;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ComponentLocator;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.PageName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.components.Component;
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
            locator = @FindBy(xpath = "./div")
    )
    @Required
    public AppInfoBlock appInfoBlock;

    @ElementName("Map Block")
    @FindBy(id = "map-block")
    public ScpMapBlock mapBlock;

    public static class SeedBlock extends HtmlElement {

        @ElementName("Vanilla seed input")
        @FindBy(id = "prompt")
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

    }
}
