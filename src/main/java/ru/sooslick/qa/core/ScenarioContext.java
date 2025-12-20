package ru.sooslick.qa.core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import ru.sooslick.qa.core.helper.NameChainHelper;
import ru.sooslick.qa.core.helper.ReflectionsHelper;
import ru.sooslick.qa.pagemodel.page.Page;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Class that stores all data related to single test execution.
 */
@Getter
@Setter
@RequiredArgsConstructor
public class ScenarioContext {

    @Getter(AccessLevel.NONE)
    private final Map<String, Object> variables = new HashMap<>();

    // cucumber creates a new factory for each test, I've made an assumption that this is start of test execution
    private long testStartTime = System.currentTimeMillis();
    private WebDriver webDriver;
    private Page loadedPage;

    /**
     * Stores variable in scenario context.
     *
     * @param variable name of the variable.
     * @param value    variable value to save.
     */
    public void saveVariable(String variable, Object value) {
        variables.put(variable, value);
    }

    /**
     * Returns a stored property (or any of its child properties)
     *
     * @param chainString name the stored variable, or path to child property (with parts separated via "->" arrow)
     * @return value of the context variable.
     */
    public @Nullable Object getVariable(String chainString) {
        LinkedList<String> chain = NameChainHelper.getChainLinks(chainString);
        String firstLink = chain.removeFirst();
        if (!variables.containsKey(firstLink))
            throw new IllegalArgumentException("Variable " + firstLink + " is not set during text execution");
        Object object = variables.get(firstLink);
        return ReflectionsHelper.getChildProperty(object, chain);
    }

    /**
     * todo javadok
     */
    public Collection<?> getVariableAsCollection(String chainString) {
        Object candidate = getVariable(chainString);
        if (!(candidate instanceof Collection<?> collection))
            throw new IllegalArgumentException("Variable '" + chainString + "' is not Collection");
        return collection;
    }
}
