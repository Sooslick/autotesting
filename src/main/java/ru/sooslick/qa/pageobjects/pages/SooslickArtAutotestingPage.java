package ru.sooslick.qa.pageobjects.pages;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.PageName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.element.HtmlElement;
import ru.sooslick.qa.pagemodel.page.AbstractPage;

@PageName("Sooslick.Art Project - Autotesting")
public class SooslickArtAutotestingPage extends AbstractPage {

    @ElementName("Number of tests text")
    @FindBy(xpath = "//*[@class='splash__title']")
    @Required
    public HtmlElement numberOfTests;

    @ElementName("Success percentage text")
    @FindBy(xpath = "//*[@class='chart__caption']")
    @Required
    public HtmlElement passedPercent;
}
