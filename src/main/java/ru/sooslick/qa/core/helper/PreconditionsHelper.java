package ru.sooslick.qa.core.helper;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.junit.platform.commons.util.ClassFilter;
import org.junit.platform.commons.util.ReflectionUtils;
import ru.sooslick.qa.pagemodel.annotations.PreconditionName;
import ru.sooslick.qa.pagemodel.precondition.Precondition;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class PreconditionsHelper {
    private final Map<String, Class<? extends Precondition>> PRECONDITION_TYPES = new HashMap<>();

    static {
        // todo config
        ReflectionUtils.streamAllClassesInPackage("ru.sooslick.qa.pagemodel.precondition",
                        ClassFilter.of(Precondition.class::isAssignableFrom))
                .filter(aClass -> aClass.getAnnotation(PreconditionName.class) != null)
                .map(aClass -> (Class<? extends Precondition>) aClass)
                .forEach(aClass -> PRECONDITION_TYPES.put(
                        aClass.getAnnotation(PreconditionName.class).value(), aClass));
    }

    @SneakyThrows
    public Precondition findByName(String name) {
        Class<? extends Precondition> type = PRECONDITION_TYPES.get(name);
        if (type == null)
            throw new IllegalArgumentException("Unknown precondition: " + name);
        return type.getDeclaredConstructor().newInstance();
    }
}
