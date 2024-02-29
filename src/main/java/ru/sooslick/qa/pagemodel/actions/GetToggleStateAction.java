package ru.sooslick.qa.pagemodel.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.sooslick.qa.core.helper.NumberHelper;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

public class GetToggleStateAction implements ActionPerformer<Boolean> {

    @Override
    public Boolean perform(HtmlElement element) {
        // todo kinda specific setup from my site ?
        WebElement toggleThumb = element.findElement(By.xpath("./*[1]"));
        String left = toggleThumb.getCssValue("left");
        String probablyNumber = NumberHelper.extractNumber(left);
        return NumberHelper.tryParseInteger(probablyNumber, 0) > 2;
    }
}
