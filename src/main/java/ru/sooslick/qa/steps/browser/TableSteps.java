package ru.sooslick.qa.steps.browser;

import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.assertions.SortingVerifier;
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
            List<String> actualHeaders = tableElement.getTableHeaders();
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
}
