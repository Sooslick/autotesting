package ru.sooslick.qa.pageobjects.blocks;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.element.HtmlElement;
import ru.sooslick.qa.pagemodel.element.ImageElement;

@ElementName("Project link Block")
public class ProjectLinkBlock extends HtmlElement {

    @ElementName("Project Image")
    @FindBy(xpath = "./img")
    public ImageElement projectImage;

    @ElementName("Click to see Button")
    @FindBy(xpath = "./button")
    public HtmlElement clickToSeeButton;
}
