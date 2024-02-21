package ru.sooslick.qa.core.generator;

import lombok.NoArgsConstructor;
import ru.sooslick.qa.core.ScenarioContext;

import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor
@GeneratorName("current date in format")
public class CurrentDateDataGenerator implements DataGenerator {
    @Override
    public String generate(String dateFormat, ScenarioContext context) {
        return new SimpleDateFormat(dateFormat).format(new Date());
    }
}
