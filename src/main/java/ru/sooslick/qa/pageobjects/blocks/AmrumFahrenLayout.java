package ru.sooslick.qa.pageobjects.blocks;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

public class AmrumFahrenLayout extends HtmlElement {
    @ElementName("Header section")
    @FindBy(xpath = "./div[1]")
    public HtmlElement headerSection;

    @ElementName("Header image")
    @FindBy(xpath = ".//div[@data-styled-section-id and contains(@style, 'url')]")
    public HtmlElement headerBackground;

    @ElementName("Header clipped area")
    @FindBy(css = ".section-geometry-slanted+.section>.section-inner,.section-geometry-slanted+main>.section:first-child>.section-inner")
    public HtmlElement headerClippedArea;
}
