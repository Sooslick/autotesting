package ru.sooslick.qa.pageobjects.pages.amrumfahren;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.PageName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

@PageName("Amrum A nach B - FAQ")
public class AmrumFahrenFaqPage extends AbstractAmrumFahrenPage {

    @ElementName("Fragen Header")
    @FindBy(xpath = "//*[text()='Häufig gestellte Fragen']")
    @Required
    public HtmlElement pageHeader;

    @ElementName("Fragen subheader")
    @FindBy(xpath = "//*[text()='...selbstverständlich mit Antwort']")
    @Required
    public HtmlElement pageSubheader;
}
