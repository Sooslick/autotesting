package ru.sooslick.qa.steps;

import io.cucumber.java.en.Given;
import org.junit.jupiter.api.AssertionFailureBuilder;
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
        Collection<?> result = source;
        do {
            String chainProperty = chain.removeFirst();
            result = result.stream()
                    .map(obj -> tryGetProperty(chainProperty, obj))
                    .collect(Collectors.toList());
        } while (chain.size() > 0);
        context.saveVariable(targetVariableName, result);
    }

    private Object tryGetProperty(String property, Object object) {
        try {
            return ReflectionsHelper.reflectiveGet(property, object);
        } catch (Exception e) {
            throw AssertionFailureBuilder.assertionFailure()
                    .message("Can't read variable property " + property)
                    .cause(e)
                    .build();
        }
    }
}
