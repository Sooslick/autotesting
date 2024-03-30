package ru.sooslick.qa.pagemodel.generator;

import lombok.NoArgsConstructor;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.helper.NameChainHelper;
import ru.sooslick.qa.core.helper.ReflectionsHelper;
import ru.sooslick.qa.pagemodel.annotations.GeneratorName;

import java.util.LinkedList;
import java.util.Optional;

/**
 * Data generator for referring to scenario context's variables.
 */
@NoArgsConstructor
@GeneratorName("variable")
public class VariableDataGenerator implements DataGenerator {

    @Override
    public String generate(String source, ScenarioContext context) {
        LinkedList<String> chain = NameChainHelper.getChainLinks(source);
        String mapKey = chain.removeFirst();
        Object variable = Optional.ofNullable(context.getVariable(mapKey))
                .orElseThrow(() -> new IllegalArgumentException("Variable is not set in test context: " + mapKey));
        while (chain.size() > 0) {
            String propertyName = chain.removeFirst();
            try {
                variable = ReflectionsHelper.reflectiveGet(propertyName, variable);
            } catch (Exception e) {
                throw new IllegalArgumentException("Unknown property: " + propertyName, e);
            }
        }
        return variable.toString();
    }
}
