package ru.sooslick.qa.steps;

import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.cucumber.java.Scenario;
import ru.sooslick.qa.core.NumberComparisonMethod;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.generator.DataGenerators;

import java.util.Arrays;

public class ParameterTypes {
    private ScenarioContext context;

    // todo context copypaste everywhere
    @Before
    public void updateContext(Scenario scenario) {
        if (context == null)
            context = ScenarioContext.getContext(scenario);
    }

    // todo i think i can simplify that
    @ParameterType("(equals to|not equals to|equals or bigger than|bigger than|equals or lesser than|lesser than)")
    public NumberComparisonMethod numberComparisonMethod(String descriptor) {
        return Arrays.stream(NumberComparisonMethod.values())
                .filter(method -> method.getWord().equals(descriptor))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown comparsion method: " + descriptor));
    }

    @ParameterType("\"(.*)\"")
    public String dataGenerator(String descriptor) {
        return DataGenerators.processString(descriptor, context);
    }
}
