package ru.sooslick.qa.core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import ru.sooslick.qa.pagemodel.page.Page;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
public class ScenarioContext {

    @Getter(AccessLevel.NONE)
    private final Map<String, Object> variables = new HashMap<>();

    private WebDriver webDriver;
    private Page loadedPage;

    public void saveVariable(String variable, Object value) {
        variables.put(variable, value);
    }

    public @Nullable Object getVariable(String variable) {
        return variables.get(variable);
    }
}
