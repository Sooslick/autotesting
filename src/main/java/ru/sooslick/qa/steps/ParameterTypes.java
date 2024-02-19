package ru.sooslick.qa.steps;

import io.cucumber.java.ParameterType;
import ru.sooslick.qa.core.NumberComparisonMethod;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.generator.DataGenerators;
import ru.sooslick.qa.core.helper.HtmlElementHelper;
import ru.sooslick.qa.pagemodel.HtmlElement;
import ru.sooslick.qa.pagemodel.annotations.Context;

import java.util.Arrays;

public class ParameterTypes {

    @Context
    private ScenarioContext context;

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

    @ParameterType("\"(.*)\"")
    public HtmlElement element(String descriptor) {
        return HtmlElementHelper.findElementByName(context.getLoadedPage(), descriptor);
    }
}
