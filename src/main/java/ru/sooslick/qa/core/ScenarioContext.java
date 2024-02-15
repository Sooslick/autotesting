package ru.sooslick.qa.core;

import io.cucumber.java.Scenario;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import ru.sooslick.qa.pagemodel.Page;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
public class ScenarioContext {
    private static final Map<String, ScenarioContext> savedContext = new HashMap<>();

    private final String scenarioId;
    @Getter(AccessLevel.NONE)
    private final Map<String, Object> variables = new HashMap<>();

    private WebDriver webDriver;
    private Page loadedPage;

    public static ScenarioContext getContext(Scenario scenario) {
        return savedContext.computeIfAbsent(scenario.getId(), ScenarioContext::new);
    }

    public static void remove(Scenario scenario) {
        savedContext.remove(scenario.getId());
    }

    public void saveVariable(String variable, Object value) {
        variables.put(variable, value);
    }

    public @Nullable Object getVariable(String variable) {
        return variables.get(variable);
    }
}
