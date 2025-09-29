package ru.sooslick.qa.pageobjects.pages.amrumfahren;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.PageName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

@PageName("Amrum A nach B - About Us")
public class AmrumFahrenAboutPage extends AbstractAmrumFahrenPage {

    @ElementName("About Us Header")
    @FindBy(xpath = "//strong[text()='Über uns']")
    @Required
    public HtmlElement pageHeader;
}
