package ru.sooslick.qa.pageobjects.pages.amrumfahren;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.PageName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

@PageName("Amrum A nach B - Events")
public class AmrumFahrenEventsPage extends AbstractAmrumFahrenPage {

    @ElementName("For organizers Header")
    @FindBy(xpath = "//*[text()='Wichtiges für Veranstalter']")
    @Required
    public HtmlElement pageHeader;

    @ElementName("For organizers subheader")
    @FindBy(xpath = "//*[text()='Ein offener Brief an Kino-, Hotel- und Restaurantbetreiber']")
    @Required
    public HtmlElement pageSubheader;
}
