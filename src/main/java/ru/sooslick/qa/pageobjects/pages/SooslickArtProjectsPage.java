package ru.sooslick.qa.pageobjects.pages;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.actions.ActionType;
import ru.sooslick.qa.pagemodel.actions.GetToggleStateAction;
import ru.sooslick.qa.pagemodel.annotations.Action;
import ru.sooslick.qa.pagemodel.annotations.ComponentLocator;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.PageName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.components.Component;
import ru.sooslick.qa.pagemodel.element.HtmlElement;
import ru.sooslick.qa.pageobjects.blocks.ProjectLinkBlock;

@PageName("Sooslick.Art Project - Projects page")
public class SooslickArtProjectsPage extends SooslickArtAbstractPage {

    @ElementName("About us Text Block")
    @FindBy(id = "about-us-text")
    @Required
    public HtmlElement aboutUsTextBlock;

    @ElementName("Hidden projects Text Block")
    @FindBy(id = "hidden-projects-text")
    @Required
    public HtmlElement hiddenProjectsTextBlock;

    @ElementName("Hidden projects Toggle")
    @FindBy(id = "bSel")
    @Action(type = ActionType.GET_CHECKBOX_STATE, performer = GetToggleStateAction.class)
    @Required
    public HtmlElement hiddenProjectsToggle;

    @ElementName("Projects List")
    @FindBy(id = "projects-list")
    @ComponentLocator(
            component = Component.LIST_ITEM,
            type = ProjectLinkBlock.class,
            locator = @FindBy(xpath = "./a[not(@invisible)]"))
    @Required
    public HtmlElement projectsList;
}
