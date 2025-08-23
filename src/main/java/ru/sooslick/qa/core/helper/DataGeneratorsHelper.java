package ru.sooslick.qa.core.helper;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.junit.platform.commons.util.ClassFilter;
import org.junit.platform.commons.util.ReflectionUtils;
import ru.sooslick.qa.core.RunnerProperties;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.pagemodel.annotations.GeneratorName;
import ru.sooslick.qa.pagemodel.generator.DataGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Helper class to the {@link DataGenerator feature}
 */
@UtilityClass
public class DataGeneratorsHelper {
    private final Pattern BRACKETS_PATTERN = Pattern.compile("\\{(.*?)}");
    private final Map<String, DataGenerator> GENERATORS = new HashMap<>();

    static {
        //noinspection unchecked
        RunnerProperties.DATA_GENERATORS_PACKAGES.forEach(pkg ->
                ReflectionUtils.streamAllClassesInPackage(pkg, ClassFilter.of(DataGenerator.class::isAssignableFrom))
                        .map(aClass -> (Class<? extends DataGenerator>) aClass)
                        .forEach(DataGeneratorsHelper::createGenerator));
    }

    /**
     * Transforms curly braces template to new value, provided by specified data generator.
     *
     * @param source  template string.
     * @param context current scenario context.
     *                Depending on implementation of data generator, values might be generated using scenario context variables.
     * @return generated string.
     */
    public String processString(String source, ScenarioContext context) {
        Matcher m = BRACKETS_PATTERN.matcher(source);
        return m.replaceAll(matchResult -> Matcher.quoteReplacement(extractBrackets(matchResult, context)));
    }

    /**
     * Transforms curly braces template to new value, provided by specified data generator,
     * and then converts it to int type
     *
     * @param source  template string.
     * @param context current scenario context.
     *                Depending on implementation of data generator, values might be generated using scenario context variables.
     * @return generated number.
     * @throws IllegalArgumentException if template cannot be processed or generated value cannot be converted to integer
     */
    public int processInteger(String source, ScenarioContext context) {
        String probablyInteger = processString(source, context);
        try {
            return Integer.parseInt(probablyInteger);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Can't convert value to number: " + source);
        }
    }

    public List<String> processList(List<String> sources, ScenarioContext context) {
        return sources.stream()
                .map(template -> processString(template, context))
                .collect(Collectors.toList());
    }

    private String extractBrackets(MatchResult matchResult, ScenarioContext context) {
        String source = matchResult.group(1);
        String[] parts = source.split(":", 2);
        String generatorName = parts[0].trim();
        String generatorData = parts.length > 1 ? parts[1].trim() : "";
        return Optional.ofNullable(generate(generatorName, generatorData, context))
                .orElse(matchResult.group(0));
    }

    // todo should I print resulting values to report?
    private String generate(String generatorName, String source, ScenarioContext context) {
        DataGenerator dataGenerator = GENERATORS.get(generatorName);
        if (dataGenerator == null)
            return null;
        return dataGenerator.generate(source, context);
    }

    @SneakyThrows
    private void createGenerator(Class<? extends DataGenerator> generatorClass) {
        DataGenerator generator;
        try {
            generator = generatorClass.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            return;
        }
        String name = Optional.ofNullable(generatorClass.getAnnotation(GeneratorName.class))
                .map(GeneratorName::value)
                .orElse(generatorClass.getSimpleName());
        if (GENERATORS.containsKey(name))
            throw new IllegalStateException("DataGenerators name duplication: " + name);
        GENERATORS.put(name, generator);
    }
}
