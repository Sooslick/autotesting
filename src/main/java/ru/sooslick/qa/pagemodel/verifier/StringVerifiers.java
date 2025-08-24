package ru.sooslick.qa.pagemodel.verifier;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.junit.platform.commons.support.scanning.ClassFilter;
import org.junit.platform.commons.util.ReflectionUtils;
import ru.sooslick.qa.core.RunnerProperties;
import ru.sooslick.qa.pagemodel.annotations.StringVerifierName;

import java.util.HashMap;
import java.util.Optional;

/**
 * Utility class that stores StringVerifier method instances
 */
@UtilityClass
public class StringVerifiers {
    private final HashMap<String, StringVerifierMethod> knownMethods = new HashMap<>();

    static {
        // todo check DataGenerator code duplication
        //noinspection unchecked
        RunnerProperties.VERIFIERS_PACKAGES.forEach(pkg ->
                ReflectionUtils.streamAllClassesInPackage(pkg, ClassFilter.of(StringVerifierMethod.class::isAssignableFrom))
                        .map(aClass -> (Class<? extends StringVerifierMethod>) aClass)
                        .forEach(StringVerifiers::newInstance));
    }

    /**
     * Returns StringVerifier method by associated name
     *
     * @param methodName name of verifier method
     * @return method instance
     */
    public StringVerifierMethod getMethod(String methodName) {
        return Optional.ofNullable(knownMethods.get(methodName.toLowerCase()))
                .orElseThrow(() -> new IllegalArgumentException("Unknown string verifier method: " + methodName));
    }

    @SneakyThrows
    private void newInstance(Class<? extends StringVerifierMethod> aClass) {
        StringVerifierName name = aClass.getAnnotation(StringVerifierName.class);
        if (name != null)
            knownMethods.put(name.value().toLowerCase(), aClass.getDeclaredConstructor().newInstance());
    }
}
