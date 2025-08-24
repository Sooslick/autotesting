package ru.sooslick.qa.core.helper;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.junit.platform.commons.support.scanning.ClassFilter;
import org.junit.platform.commons.util.ReflectionUtils;
import ru.sooslick.qa.core.RunnerProperties;
import ru.sooslick.qa.pagemodel.annotations.PreconditionName;
import ru.sooslick.qa.pagemodel.precondition.Precondition;

import java.util.HashMap;
import java.util.Map;

/**
 * Helper class for {@link Precondition} feature.
 */
@UtilityClass
public class PreconditionsHelper {
    private final Map<String, Class<? extends Precondition>> PRECONDITION_TYPES = new HashMap<>();

    static {
        //noinspection unchecked
        RunnerProperties.PRECONDITIONS_PACKAGES.forEach(pkg ->
                ReflectionUtils.streamAllClassesInPackage(pkg, ClassFilter.of(Precondition.class::isAssignableFrom))
                        .filter(aClass -> aClass.getAnnotation(PreconditionName.class) != null)
                        .map(aClass -> (Class<? extends Precondition>) aClass)
                        .forEach(aClass -> PRECONDITION_TYPES.put(
                                aClass.getAnnotation(PreconditionName.class).value(), aClass)));
    }

    /**
     * Return new Precondition instance, annotated with PreconditionName with name that equals to given parameter.
     *
     * @param name precondition name.
     * @return precondition instance for given name.
     * @throws IllegalArgumentException if no preconditions found by given name.
     */
    @SneakyThrows
    public Precondition findByName(String name) {
        Class<? extends Precondition> type = PRECONDITION_TYPES.get(name);
        if (type == null)
            throw new IllegalArgumentException("Unknown precondition: " + name);
        return type.getDeclaredConstructor().newInstance();
    }
}
