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

/**
 * Helper class for Item List elements
 */
@UtilityClass
public class ItemListHelper {

    /**
     * Method finds all elements in container by LI_COMPONENT locator
     * and generates HtmlElement instances of type, specified in LI_COMPONENT, for all found list items.
     *
     * @param listContainer item list element.
     * @return list of found and wrapped list items.
     */
    public List<HtmlElement> getListItems(HtmlElement listContainer) {
        List<WebElement> listItemsRaw = listContainer.findComponentElements(Component.LIST_ITEM);
        List<HtmlElement> result = new LinkedList<>();
        for (WebElement liElement : listItemsRaw) {
            HtmlElement wrappedElement = wrapLiElement(listContainer, liElement);
            result.add(wrappedElement);
        }
        return result;
    }

    @SneakyThrows
    private HtmlElement wrapLiElement(HtmlElement listContainer, WebElement li) {
        HtmlElement wrappedElement = new HtmlElementBuilder(listContainer.getComponentType(Component.LIST_ITEM))
                .webDriver(listContainer.getWrappedDriver())
                .parent(li)
                .locator(By.xpath("./."))   // kinda weird, but I can't control over liElement locator
                .build();
        FieldDecorator decorator = new PageFieldDecorator(
                listContainer.getWrappedDriver(),
                wrappedElement, wrappedElement);
        PageFactory.initElements(decorator, wrappedElement);
        return wrappedElement;
    }
}
