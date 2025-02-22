package ru.sooslick.qa.pagemodel.generator;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.pagemodel.annotations.GeneratorName;

/**
 * Data generator for providing random characters using Apache Commons Random Strings
 */
@NoArgsConstructor
@GeneratorName("random ascii string")
public class RandomStringDataGenerator implements DataGenerator {

    private static final RandomStringUtils random = RandomStringUtils.insecure();

    @Override
    public String generate(String source, ScenarioContext context) {
        return random.nextAscii(Integer.parseInt(source));
    }
}
