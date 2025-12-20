package ru.sooslick.qa.steps;

import io.cucumber.java.en.Given;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.helper.NameChainHelper;
import ru.sooslick.qa.core.helper.ReflectionsHelper;
import ru.sooslick.qa.pagemodel.annotations.Context;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class ScenarioContextSteps {

    @Context
    private ScenarioContext context;

    @Given("A user remembers the value {dataGenerator} as variable {string}")
    public void saveVariable(String value, String variableName) {
        context.saveVariable(variableName, value);
    }

    @Given("A user remembers the following text as variable {string}")
    public void saveTextVariable(String variableName, String value) {
        context.saveVariable(variableName, value);
    }

    @Given("A user reads property {string} of each object from list variable {listVariable} and saves result to variable {string}")
    public void mapListVariable(String propertyName, Collection<?> source, String targetVariableName) {
        LinkedList<String> chain = NameChainHelper.getChainLinks(propertyName);
        if (chain.size() == 0)
            throw new IllegalArgumentException("Invalid variable property: " + propertyName);
        Object result = source.stream()
                .map(obj -> ReflectionsHelper.getChildProperty(obj, new LinkedList<>(chain)))
                .collect(Collectors.toList());
        context.saveVariable(targetVariableName, result);
    }

    @Given("A user performs regex find {string} and replace to {string} for each entry in list variable {string}")
    public void replaceAllListVariable(String find, String replace, String variableName) {
        Object result = context.getVariableAsCollection(variableName)
                .stream()
                .map(obj -> String.valueOf(obj).replaceAll(find, replace))
                .collect(Collectors.toList());
        context.saveVariable(variableName, result);
    }
}
