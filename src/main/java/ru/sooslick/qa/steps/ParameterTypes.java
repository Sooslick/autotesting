package ru.sooslick.qa.steps;

import io.cucumber.java.ParameterType;
import lombok.extern.slf4j.Slf4j;
import ru.sooslick.qa.core.Alignment;
import ru.sooslick.qa.core.ContextInjector;
import ru.sooslick.qa.core.NumberComparisonMethod;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.SortingType;
import ru.sooslick.qa.core.assertions.SortingVerifier;
import ru.sooslick.qa.core.assertions.StringVerifier;
import ru.sooslick.qa.core.helper.DataGeneratorsHelper;
import ru.sooslick.qa.core.helper.HtmlElementHelper;
import ru.sooslick.qa.core.helper.PreconditionsHelper;
import ru.sooslick.qa.pagemodel.annotations.Context;
import ru.sooslick.qa.pagemodel.element.HtmlElement;
import ru.sooslick.qa.pagemodel.element.ImageElement;
import ru.sooslick.qa.pagemodel.element.TableElement;
import ru.sooslick.qa.pagemodel.precondition.Precondition;

import java.util.Arrays;
import java.util.Collection;

@Slf4j
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
    public StringVerifier stringVerifier(String descriptor) {
        return new StringVerifier(dataGenerator(descriptor));
    }

    @ParameterType("([0-9]+|\\{.*?\\})")
    public int intGenerator(String descriptor) {
        return DataGeneratorsHelper.processInteger(descriptor, context);
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
    public TableElement table(String descriptor) {
        HtmlElement element = HtmlElementHelper.findElementByName(context.getLoadedPage(), descriptor);
        if (element instanceof TableElement tableElement)
            return tableElement;
        throw new IllegalArgumentException("Element is not declared as TableElement: " + descriptor);
    }

    @ParameterType("\"(.*)\"")
    public Precondition precondition(String descriptor) {
        Precondition precondition = PreconditionsHelper.findByName(descriptor);
        ContextInjector.injectContext(precondition, context);
        return precondition;
    }

    // todo third state "neither checked nor unchecked" exists
    @ParameterType("((not )?checked)")
    public boolean checkboxState(String descriptor) {
        return !descriptor.startsWith("n");
    }

    @ParameterType("\"(.*)\"")
    public Object variable(String descriptor) {
        return context.getVariable(descriptor);
    }

    @ParameterType("\"(.*)\"")
    public Collection<?> listVariable(String descriptor) {
        return context.getVariableAsCollection(descriptor);
    }

    @ParameterType("(top|bottom|left|right)")
    public Alignment alignment(String descriptor) {
        return Alignment.valueOf(descriptor.toUpperCase());
    }

    @ParameterType("(ascending|descending)")
    public boolean sorting(String descriptor) {
        return "ascending".equals(descriptor);
    }

    @ParameterType("\"(.*)\"")
    public SortingVerifier sortingType(String descriptor) {
        try {
            return SortingType.valueOf(descriptor.toUpperCase()).create();
        } catch (IllegalArgumentException e) {
            log.warn("Unknown sorting type: " + descriptor + ", step will compare values as Strings.");
            return new SortingVerifier();
        }
    }
}
