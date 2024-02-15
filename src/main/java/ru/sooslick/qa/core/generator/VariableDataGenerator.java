package ru.sooslick.qa.core.generator;

import lombok.NoArgsConstructor;
import ru.sooslick.qa.core.ScenarioContext;

import java.util.Optional;

@NoArgsConstructor
@GeneratorName("variable")
public class VariableDataGenerator implements DataGenerator {
    @Override
    public String generate(String source, ScenarioContext context) {
        return Optional.ofNullable(context.getVariable(source))
                .map(Object::toString)
                .orElseThrow(() -> new IllegalArgumentException("Variable is not set in test context: " + source));
    }
}
