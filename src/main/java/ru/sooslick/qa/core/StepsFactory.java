package ru.sooslick.qa.core;

import io.cucumber.core.backend.ObjectFactory;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

/**
 * Custom cucumber's ObjectFactory with implemented dependency injection feature.
 */
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
            ContextInjector.injectContext(instance, context);
            // todo remove ObjectFactory property from yaml, implement @DependencyInjection feature
        }
        return instance;
    }

    @SneakyThrows
    private <T> T cacheNewInstance(Class<T> type) {
        T instance = type.getDeclaredConstructor().newInstance();
        this.instances.put(type, instance);
        return instance;
    }
}
