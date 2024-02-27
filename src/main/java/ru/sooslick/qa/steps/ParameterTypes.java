package ru.sooslick.qa.steps;

import io.cucumber.java.ParameterType;
import ru.sooslick.qa.core.ContextInjector;
import ru.sooslick.qa.core.NumberComparisonMethod;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.helper.DataGeneratorsHelper;
import ru.sooslick.qa.core.helper.HtmlElementHelper;
import ru.sooslick.qa.core.helper.PreconditionsHelper;
import ru.sooslick.qa.pagemodel.annotations.Context;
import ru.sooslick.qa.pagemodel.element.HtmlElement;
import ru.sooslick.qa.pagemodel.element.ImageElement;
import ru.sooslick.qa.pagemodel.precondition.Precondition;

import java.util.Arrays;

public class ParameterTypes {

    @Context
    private ScenarioContext context;

    //@ParameterType("((not )?(equals (to|or ))?((bigger|lesser) than)?)")
    @ParameterType("(equals to|not equals to|equals or bigger than|bigger than|equals or lesser than|lesser than)")
    public NumberComparisonMethod numberComparisonMethod(String descriptor) {
        return Arrays.stream(NumberComparisonMethod.values())
                .filter(method -> method.getWord().equals(descriptor))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown comparison method: " + descriptor));
    }

    @ParameterType("\"(.*)\"")
    public String dataGenerator(String descriptor) {
        return DataGeneratorsHelper.processString(descriptor, context);
    }

    @ParameterType("\"(.*)\"")
    public HtmlElement element(String descriptor) {
        return HtmlElementHelper.findElementByName(context.getLoadedPage(), descriptor);
    }

    @ParameterType("\"(.*)\"")
    public ImageElement image(String descriptor) {
        HtmlElement element = HtmlElementHelper.findElementByName(context.getLoadedPage(), descriptor);
        if (element instanceof ImageElement imageElement)
            return imageElement;
        throw new IllegalArgumentException("Element is not declared as ImageElement: " + descriptor);
    }

    @ParameterType("\"(.*)\"")
    public Precondition precondition(String descriptor) {
        Precondition precondition = PreconditionsHelper.findByName(descriptor);
        ContextInjector.injectContext(precondition, context);
        return precondition;
    }
}
