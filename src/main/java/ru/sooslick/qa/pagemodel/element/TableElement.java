package ru.sooslick.qa.pagemodel.element;

import org.openqa.selenium.WebElement;
import ru.sooslick.qa.pagemodel.components.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Child class of HtmlElement with some functions, related to table elements.
 */
public class TableElement extends HtmlElement {

    // todo I should have customizable actions in methods like I do in Actions
    // todo javadocs

    public ArrayList<String> getTableHeaders() {
        WebElement thead = this.findComponent(Component.TABLE_HEAD);
        WebElement headerTr = thead.findElement(this.getComponentLocator(Component.TABLE_HEAD_ROW));
        List<WebElement> ths = headerTr.findElements(this.getComponentLocator(Component.TABLE_HEAD_CELL));
        return ths.stream()
                .map(WebElement::getText)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<List<String>> getTableRows() {
        WebElement tbody = this.findComponent(Component.TABLE_BODY);
        List<WebElement> trs = tbody.findElements(this.getComponentLocator(Component.TABLE_BODY_ROW));
        List<List<String>> result = new LinkedList<>();
        for (WebElement tr : trs) {
            result.add(tr.findElements(this.getComponentLocator(Component.TABLE_BODY_CELL))
                    .stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList()));
        }
        return result;
    }

    public List<Map<String, String>> getMappedRows() {
        ArrayList<String> headers = getTableHeaders();
        List<List<String>> rows = getTableRows();
        return rows.stream()
                .map(row -> {
                    Map<String, String> mappedRow = new HashMap<>();
                    for (int i = 0; i < row.size(); i++)
                        mappedRow.put(headers.get(i), row.get(i));
                    return mappedRow;
                })
                .collect(Collectors.toList());
    }
}
