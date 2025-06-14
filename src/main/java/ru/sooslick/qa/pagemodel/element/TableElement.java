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

    /**
     * @return list of TH elements in this table
     */
    public List<WebElement> getTableHeaders() {
        WebElement thead = this.findComponent(Component.TABLE_HEAD);
        WebElement headerTr = thead.findElement(this.getComponentLocator(Component.TABLE_HEAD_ROW));
        return headerTr.findElements(this.getComponentLocator(Component.TABLE_HEAD_CELL));
    }

    /**
     * @return column names, taken from table's header cells, in order from first to last as in DOM
     */
    public ArrayList<String> getTableHeaderNames() {
        List<WebElement> ths = getTableHeaders();
        return ths.stream()
                .map(WebElement::getText)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * @return list of "tr" web elements in table
     */
    public List<WebElement> getTableRows() {
        WebElement tbody = this.findComponent(Component.TABLE_BODY);
        return tbody.findElements(this.getComponentLocator(Component.TABLE_BODY_ROW));
    }

    /**
     * @return content of each table's body row without mapping to columns
     */
    public List<List<String>> getTableRowsContent() {
        List<WebElement> trs = getTableRows();
        List<List<String>> result = new LinkedList<>();
        for (WebElement tr : trs) {
            result.add(tr.findElements(this.getComponentLocator(Component.TABLE_BODY_CELL))
                    .stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList()));
        }
        return result;
    }

    /**
     * @return content of each table's body row, mapped as column name -> row value
     */
    public List<Map<String, String>> getMappedRows() {
        ArrayList<String> headers = getTableHeaderNames();
        List<List<String>> rows = getTableRowsContent();
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
