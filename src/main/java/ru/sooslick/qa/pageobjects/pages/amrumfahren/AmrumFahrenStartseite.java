package ru.sooslick.qa.pageobjects.pages.amrumfahren;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ComponentLocator;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.PageName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.components.Component;
import ru.sooslick.qa.pagemodel.components.DefaultLiComponent;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

@PageName("Amrum A nach B - startseite")
public class AmrumFahrenStartseite extends AbstractAmrumFahrenPage {

    @ElementName("Main block")
    @FindBy(xpath = "//h3")
    @ComponentLocator(
            component = Component.LIST_ITEM,
            locator = @FindBy(xpath = ".//span"),
            type = DefaultLiComponent.class         // TODO - should loaded by default, but actually not! prob engine bug
    )
    @Required
    public HtmlElement mainBlock;

    @ElementName("Welcome block")
    @FindBy(xpath = "//div[@data-zone-type='content'][2]")
    @Required
    public HtmlElement welcomeBlock;

    @ElementName("Services block")
    @FindBy(xpath = "//div[@data-zone-type='content'][3]")
    @Required
    public HtmlElement servicesBlock;
}
