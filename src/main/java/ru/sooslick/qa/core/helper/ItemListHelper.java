package ru.sooslick.qa.core.helper;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import ru.sooslick.qa.core.page.HtmlElementBuilder;
import ru.sooslick.qa.core.page.PageFieldDecorator;
import ru.sooslick.qa.pagemodel.components.Component;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

import java.util.LinkedList;
import java.util.List;

@UtilityClass
public class ItemListHelper {

    @SneakyThrows
    public List<HtmlElement> getListItems(HtmlElement listContainer) {
        List<WebElement> listItemsRaw = listContainer.findElements(listContainer.getComponentLocator(Component.LI_ELEMENT));
        List<HtmlElement> result = new LinkedList<>();
        for (WebElement liElement : listItemsRaw) {
            HtmlElement wrapperElement = new HtmlElementBuilder(listContainer.getComponentType(Component.LI_ELEMENT))
                    .webDriver(listContainer.getWrappedDriver())
                    .parent(liElement)
                    .locator(By.xpath("./."))   // kinda weird but i can't control over li item locator
                    .build();
            FieldDecorator decorator = new PageFieldDecorator(
                    listContainer.getWrappedDriver(),
                    wrapperElement, wrapperElement);
            PageFactory.initElements(decorator, wrapperElement);
            result.add(wrapperElement);
        }
        return result;
    }
}
