package ru.sooslick.qa.core;

import io.cucumber.core.backend.ObjectFactory;
import lombok.SneakyThrows;
import ru.sooslick.qa.pagemodel.annotations.Context;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StepsFactory implements ObjectFactory {
    private final Map<Class<?>, Object> instances = new HashMap<>();
    private ScenarioContext context;

    @Override
    public void start() {
        context = new ScenarioContext();
    }

    @Override
    public void stop() {
        instances.clear();
    }

    @Override
    public boolean addClass(Class<?> aClass) {
        // javadoc claims I should always return true and ignore it
        return true;
    }

    @Override
    public <T> T getInstance(Class<T> type) {
        T instance = type.cast(this.instances.get(type));
        if (instance == null) {
            instance = this.cacheNewInstance(type);
            injectContext(instance);
        }
        return instance;
    }

    @SneakyThrows
    private <T> T cacheNewInstance(Class<T> type) {
        T instance = type.getDeclaredConstructor().newInstance();
        this.instances.put(type, instance);
        return instance;
    }


    private void injectContext(Object victim) {
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
