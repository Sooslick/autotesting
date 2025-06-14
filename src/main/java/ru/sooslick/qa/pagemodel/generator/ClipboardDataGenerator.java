package ru.sooslick.qa.pagemodel.generator;

import lombok.NoArgsConstructor;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.pagemodel.annotations.GeneratorName;
import ru.sooslick.qa.steps.ClipboardSteps;

/**
 * Data generator - using text values from system clipboard
 */
@NoArgsConstructor
@GeneratorName("clipboard")
public class ClipboardDataGenerator implements DataGenerator {

    @Override
    public String generate(String source, ScenarioContext context) {
        return ClipboardSteps.getClipboardText();
    }
}
