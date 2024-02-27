package ru.sooslick.qa.steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.helper.DataGeneratorsHelper;
import ru.sooslick.qa.pagemodel.annotations.Context;
import ru.sooslick.qa.pagemodel.precondition.Precondition;

import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

public class PreconditionSteps {
    private final LinkedList<Precondition> completedPreconditions = new LinkedList<>();

    @Context
    private ScenarioContext context;

    @Given("A user fulfills the precondition {precondition}")
    public void completePrecondition(Precondition precondition, Map<String, String> dataTable) {
        Map<String, String> processedTable = dataTable.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        (e) -> e.getKey(),
                        (e) -> DataGeneratorsHelper.processString(e.getValue(), context)));
        precondition.withParameters(processedTable);
        completedPreconditions.add(precondition);
        precondition.complete();
    }

    @After
    public void rollback() {
        while (completedPreconditions.size() > 0)
            completedPreconditions.removeFirst().rollback();
    }
}
