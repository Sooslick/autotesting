package ru.sooslick.qa.pagemodel.generator;

import ru.sooslick.qa.core.ScenarioContext;

public interface DataGenerator {
    String generate(String source, ScenarioContext context);
}
