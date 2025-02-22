package ru.sooslick.qa.core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import ru.sooslick.qa.pagemodel.page.Page;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
     * @param variable name of the variable.
     * @return value of the context variable.
     */
    public @Nullable Object getVariable(String variable) {
        return variables.get(variable);
    }

    /**
     * @param variable     name of the variable.
     * @param defaultValue fallback value if variable not defined in this session
     * @return value of the context variable if exists, defaultValue otherwise.
     */
    public @NotNull Object getVariable(String variable, @NotNull Object defaultValue) {
        return Optional.ofNullable(variables.get(variable))
                .orElse(defaultValue);
    }
}
