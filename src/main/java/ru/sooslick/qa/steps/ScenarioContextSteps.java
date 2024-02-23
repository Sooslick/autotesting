package ru.sooslick.qa.steps;

import io.cucumber.java.en.Given;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.pagemodel.annotations.Context;

public class ScenarioContextSteps {

    @Context
    private ScenarioContext context;

    @Given("A user remembers the value {dataGenerator} as variable {string}")
    public void saveVariable(String value, String variableName) {
        context.saveVariable(variableName, value);
    }
}
