package ru.sooslick.qa.pagemodel.generator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.NoArgsConstructor;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.helper.JsonHelper;
import ru.sooslick.qa.pagemodel.annotations.GeneratorName;

import java.util.Optional;

/**
 * Data generator for referring to scenario context's JSON variables (with json path support)
 */
@NoArgsConstructor
@GeneratorName("json variable")
public class JsonVariableDataGenerator implements DataGenerator {

    @Override
    public String generate(String source, ScenarioContext context) {
        String[] parts = source.split("/", 2);
        String varName = parts[0];
        String jsonPath = parts.length > 1 ? "/" + parts[1] : null;

        Object variable = Optional.ofNullable(context.getVariable(varName))
                .orElseThrow(() -> new IllegalArgumentException("Variable is not set in test context: " + varName));

        JsonNode node;
        if (!(variable instanceof JsonNode)) {
            try {
                node = JsonHelper.OBJECT_MAPPER.readTree(variable.toString());
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException("Variable is not valid JSON: " + varName);
            }
        } else
            node = (JsonNode) variable;

        if (jsonPath == null)
            return node.asText();
        else
            return node.at(jsonPath).asText();
    }
}
