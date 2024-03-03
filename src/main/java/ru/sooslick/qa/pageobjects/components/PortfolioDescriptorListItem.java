package ru.sooslick.qa.pageobjects.components;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

public class PortfolioDescriptorListItem extends HtmlElement {

    @ElementName("White dot")
    @FindBy(xpath = "./*[@class='portfolio-dot']")
    public HtmlElement dot;

    @ElementName("Text line")
    @FindBy(xpath = "./*[@class='portfolio-dot']")
    public HtmlElement lineText;
}
