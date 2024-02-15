package ru.sooslick.qa.core.generator;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.helper.ReflectionsHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class DataGenerators {
    private final Pattern BRACKETS_PATTERN = Pattern.compile("\\{(.*?)}");
    private final Map<String, DataGenerator> GENERATORS = new HashMap<>();

    static {
        // todo config
        ReflectionsHelper.getPackageClasses("ru.sooslick.qa.core.generator", DataGenerator.class)
                .forEach(DataGenerators::createGenerator);
    }

    public String processString(String source, ScenarioContext context) {
        Matcher m = BRACKETS_PATTERN.matcher(source);
        return m.replaceAll(matchResult -> extractBrackets(matchResult, context));
    }

    private String extractBrackets(MatchResult matchResult, ScenarioContext context) {
        String source = matchResult.group(1);
        String[] parts = source.split(":", 2);
        String generatorName = parts[0].trim();
        String generatorData = parts.length > 1 ? parts[1].trim() : "";
        return Optional.ofNullable(generate(generatorName, generatorData, context))
                .orElse(matchResult.group(0));
    }

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
