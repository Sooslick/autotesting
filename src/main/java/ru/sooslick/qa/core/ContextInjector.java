package ru.sooslick.qa.core;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import ru.sooslick.qa.pagemodel.annotations.Context;

import java.lang.reflect.Field;
import java.util.Arrays;

@UtilityClass
public class ContextInjector {
    // todo I can refactor ScenarioContext param usage
    public void injectContext(Object victim, ScenarioContext context) {
        Arrays.stream(victim.getClass().getDeclaredFields())
                .filter(f -> f.getAnnotation(Context.class) != null)
                .filter(f -> f.getType().isAssignableFrom(ScenarioContext.class))
                .forEach(f -> set(f, victim, context));
    }

    @SneakyThrows
    private void set(Field f, Object o, Object v) {
        f.setAccessible(true);
        f.set(o, v);
    }
}
