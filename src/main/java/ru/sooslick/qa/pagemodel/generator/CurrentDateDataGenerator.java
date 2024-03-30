package ru.sooslick.qa.pagemodel.generator;

import lombok.NoArgsConstructor;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.pagemodel.annotations.GeneratorName;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Data generator for formatting various dates in cucumber scenarios.
 */
@NoArgsConstructor
@GeneratorName("current date in format")
public class CurrentDateDataGenerator implements DataGenerator {
    @Override
    public String generate(String dateFormat, ScenarioContext context) {
        return new SimpleDateFormat(dateFormat).format(new Date());
    }
}
