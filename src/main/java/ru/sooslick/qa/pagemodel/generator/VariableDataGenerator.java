package ru.sooslick.qa.pagemodel.generator;

import lombok.NoArgsConstructor;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.pagemodel.annotations.GeneratorName;

/**
 * Data generator for referring to scenario context's variables.
 */
@NoArgsConstructor
@GeneratorName("variable")
public class VariableDataGenerator implements DataGenerator {

    @Override
    public String generate(String source, ScenarioContext context) {
        Object something = context.getVariable(source);
        return something == null ? "[null]" : something.toString();
    }
}
