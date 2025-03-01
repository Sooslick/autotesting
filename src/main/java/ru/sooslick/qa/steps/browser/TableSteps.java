package ru.sooslick.qa.steps.browser;

import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.assertions.SortingVerifier;
import ru.sooslick.qa.core.assertions.StringVerifier;
import ru.sooslick.qa.core.helper.DataGeneratorsHelper;
import ru.sooslick.qa.core.repeaters.Repeat;
import ru.sooslick.qa.pagemodel.annotations.Context;
import ru.sooslick.qa.pagemodel.components.Component;
import ru.sooslick.qa.pagemodel.element.TableElement;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// todo javadocs for steps classes

public class TableSteps {

    @Context
    private ScenarioContext context;

    @Then("Table header of {table} has following headers")
    public void checkTableContainsHeaders(TableElement tableElement, List<String> expectedHeadersRaw) {
        // todo repeating block of code, refactor plz
        List<String> expectedHeaders = expectedHeadersRaw.stream()
                .map(s -> DataGeneratorsHelper.processString(s, context))
                .collect(Collectors.toList());

        Repeat.untilSuccess(() -> {
            List<String> actualHeaders = tableElement.getTableHeaderNames();
            Assertions.assertTrue(actualHeaders.containsAll(expectedHeaders));
        });
    }

    @Then("Table {table} has rows")
    public void checkTableHasRows(TableElement tableElement) {
        Repeat.untilSuccess(() -> {
            WebElement tbody = tableElement.findComponent(Component.TABLE_BODY);
            List<WebElement> trs = tbody.findElements(tableElement.getComponentLocator(Component.TABLE_BODY_ROW));
            Assertions.assertTrue(trs.size() > 0);
        });
    }

    // todo: can I merge sortingtype to column property?
    @Then("Table {table} sorted by {dataGenerator} column in {sorting} order, comparing as {sortingType}")
    public void checkTableSorting(TableElement tableElement, String sortingColumn, boolean ascending, SortingVerifier sortingVerifier) {
        Repeat.untilSuccess(() -> {
            List<Map<String, String>> table = tableElement.getMappedRows();
            List<String> columnValues = table.stream()
                    .map(row -> row.get(sortingColumn))
                    .collect(Collectors.toList());
            sortingVerifier.verifySorted(columnValues, ascending);
        });
    }

    @Then("{table} table header has a CSS-property {string} with value {dataGenerator}")
    public void checkTableHeaderCss(TableElement tableElement, String property, String expectedTemplate) {
        StringVerifier expected = new StringVerifier(expectedTemplate);

        Repeat.untilSuccess(() -> {
            // check order: th first as main method (todo unimplemented - thead then if first method fails)
            List<WebElement> ths = tableElement.getTableHeaders();
            ths.forEach(th -> expected.test(th.getCssValue(property)));
        });
    }

    @Then("Table {table} content matches variable {string}, using following mapping")
    public void checkTableContent(TableElement tableElement, String selectionName, Map<String, String> mapping) {
        List<Map<String, Object>> expectedRows;
        try {
            //noinspection unchecked
            expectedRows = (List<Map<String, Object>>) context.getVariable(selectionName);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("variable does not contain rows");
        }

        if (expectedRows == null)
            throw new IllegalArgumentException("Context variable " + selectionName + " is not set");

        Repeat.untilSuccess(() -> {
            List<Map<String, String>> actualRows = tableElement.getMappedRows();
            Assertions.assertEquals(expectedRows.size(), actualRows.size(), "Rows count is not equals");

            for (int i = 0; i < expectedRows.size(); i++) {
                Map<String, Object> expectedRow = expectedRows.get(i);
                Map<String, String> actualRow = actualRows.get(i);

                for (var pair : mapping.entrySet()) {
                    String actual = actualRow.get(pair.getKey());
                    Assertions.assertNotNull(actual, "Element " + tableElement.getName() + " has not headers with text " + pair.getKey());
                    String expected = expectedRow.get(pair.getValue()).toString();
                    Assertions.assertNotNull(expected, "Selection " + selectionName + " has not columns with name " + pair.getValue());
                    Assertions.assertEquals(expected, actual, "Table comparison mismatch at row " + (i + 1) + ", column " + pair.getKey());
                }
            }
        });
    }
}
