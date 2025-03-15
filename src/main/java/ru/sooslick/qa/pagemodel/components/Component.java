package ru.sooslick.qa.pagemodel.components;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

/**
 * Enumeration of various HtmlElement parts, used in page model and related steps.
 */
@RequiredArgsConstructor
@Getter
public enum Component {
    LIST_ITEM(By.xpath("./li"), DefaultLiComponent.class),
    TABLE_HEAD(By.xpath("./thead"), DefaultTableHeaderComponent.class),
    TABLE_HEAD_ROW(By.xpath("./tr"), DefaultTableRowComponent.class),
    TABLE_HEAD_CELL(By.xpath("./th"), DefaultTableCellComponent.class),
    TABLE_BODY(By.xpath("./tbody"), DefaultTableComponent.class),
    TABLE_BODY_ROW(By.xpath("./tr"), DefaultTableRowComponent.class),
    TABLE_BODY_CELL(By.xpath("./td"), DefaultTableCellComponent.class);

    private final By defaultLocator;
    private final Class<? extends HtmlElement> containerType;   // todo review usage
}
