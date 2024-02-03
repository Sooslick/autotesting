package ru.sooslick.qa.steps;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import ru.sooslick.qa.core.ScenarioContext;

public class ScenarioContextSteps {

    @After
    public void finishScenario(Scenario scenario) {
        ScenarioContext.remove(scenario);
    }
}
