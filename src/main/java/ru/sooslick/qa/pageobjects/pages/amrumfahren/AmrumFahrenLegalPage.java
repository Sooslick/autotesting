package ru.sooslick.qa.pageobjects.pages.amrumfahren;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.PageName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

@PageName("Amrum A nach B - Datenschutzerklärung")
public class AmrumFahrenLegalPage extends AbstractAmrumFahrenPage {

    @ElementName("Legal Header")
    @FindBy(xpath = "//strong[text()='Datenschutzerklärung']")
    @Required
    public HtmlElement pageHeader;
}
