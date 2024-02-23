package ru.sooslick.qa.pageobjects.components;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.actions.ActionType;
import ru.sooslick.qa.pagemodel.actions.GetHrefAttributeAction;
import ru.sooslick.qa.pagemodel.annotations.Action;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.element.HtmlElement;
import ru.sooslick.qa.pagemodel.element.ImageElement;

public class MainPageFooterItem extends HtmlElement {

    @ElementName("Link")
    @FindBy(xpath = "./.")
    @Action(type = ActionType.GET_TEXT, performer = GetHrefAttributeAction.class)
    public HtmlElement link;

    @ElementName("Image")
    @FindBy(xpath = "./img")
    public ImageElement image;
}
