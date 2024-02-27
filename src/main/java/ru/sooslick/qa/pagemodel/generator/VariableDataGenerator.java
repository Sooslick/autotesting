package ru.sooslick.qa.pagemodel.generator;

import lombok.NoArgsConstructor;
import org.junit.platform.commons.util.ReflectionUtils;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.helper.NameChainHelper;
import ru.sooslick.qa.pagemodel.annotations.GeneratorName;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Optional;

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
                variable = reflectiveGet(propertyName, variable);
            } catch (Exception e) {
                throw new IllegalArgumentException("Variable is not set in test context: " + propertyName, e);
            }
        }
        return variable.toString();
    }

    private Object reflectiveGet(String propertyName, Object source) throws Exception {
        Field f = source.getClass().getDeclaredField(propertyName);
        return ReflectionUtils.tryToReadFieldValue(f, source).get();
    }
}
