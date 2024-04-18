package ru.sooslick.qa.pageobjects.components;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ComponentLocator;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.components.Component;
import ru.sooslick.qa.pagemodel.element.HtmlElement;
import ru.sooslick.qa.pageobjects.blocks.ProjectLinkBlock;

public class PortfolioProjectListItem extends HtmlElement {

    @ElementName("Project Block")
    @FindBy(xpath = "./.")
    public ProjectLinkBlock projectBlock;

    @ElementName("Project Link")
    @FindBy(xpath = "./a")
    public ProjectLinkBlock linkBlock;

    @ElementName("Project descriptions List")
    @FindBy(xpath = "./div")
    @ComponentLocator(
            component = Component.LIST_ITEM,
            locator = @FindBy(xpath = "./div"),
            type = PortfolioDescriptorListItem.class
    )
    public HtmlElement descriptionsList;
}
