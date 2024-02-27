package ru.sooslick.qa.pagemodel.generator;

import lombok.NoArgsConstructor;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.helper.PropertiesHelper;
import ru.sooslick.qa.pagemodel.annotations.GeneratorName;

@NoArgsConstructor
@GeneratorName("property")
public class PropertiesDataGenerator implements DataGenerator {

    @Override
    public String generate(String source, ScenarioContext context) {
        return PropertiesHelper.getProperty(source);
    }
}
