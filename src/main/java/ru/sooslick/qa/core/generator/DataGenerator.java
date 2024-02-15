package ru.sooslick.qa.core.generator;

import ru.sooslick.qa.core.ScenarioContext;

public interface DataGenerator {
    String generate(String source, ScenarioContext context);
}
