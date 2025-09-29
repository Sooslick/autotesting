package ru.sooslick.qa.pageobjects.pages.amrumfahren;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.PageName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

@PageName("Amrum A nach B - Unsere Preise")
public class AmrumFahrenPreisePage extends AbstractAmrumFahrenPage {

    @ElementName("Unsere Preise Header")
    @FindBy(xpath = "//*[text()='Unsere Preise']")
    @Required
    public HtmlElement pageHeader;

    @ElementName("Unsere Preise subheader")
    @FindBy(xpath = "//*[text()='Transparente Preisgestaltung ist uns sehr wichtig!']")
    @Required
    public HtmlElement pageSubheader;
}
