package ru.sooslick.qa.core;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import ru.sooslick.qa.pagemodel.annotations.Context;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Dependency injector for Page Model classes.
 */
@UtilityClass
public class ContextInjector {

    /**
     * Injects ScenarioContext to desired object using relective access and {@link Context} annotation.
     *
     * @param victim  object which needs the injection.
     * @param context ScenarioContext that will be injected.
     */
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
