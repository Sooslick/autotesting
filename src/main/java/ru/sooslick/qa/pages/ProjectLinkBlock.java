package ru.sooslick.qa.pages;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.HtmlElement;
import ru.sooslick.qa.pagemodel.ImageElement;
import ru.sooslick.qa.pagemodel.annotations.ElementName;

@ElementName("Project link Block")
public class ProjectLinkBlock extends HtmlElement {

    @ElementName("Project Image")
    @FindBy(xpath = "./img")
    public ImageElement projectImage;

    @ElementName("Click to see Button")
    @FindBy(xpath = "./button")
    public HtmlElement clickToSeeButton;
}
