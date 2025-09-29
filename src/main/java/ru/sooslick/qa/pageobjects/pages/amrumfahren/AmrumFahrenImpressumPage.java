package ru.sooslick.qa.pageobjects.pages.amrumfahren;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.PageName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

@PageName("Amrum A nach B - Impressum")
public class AmrumFahrenImpressumPage extends AbstractAmrumFahrenPage {

    @ElementName("Impressum Header")
    @FindBy(xpath = "//strong[text()='Impressum']")
    @Required
    public HtmlElement pageHeader;
}
