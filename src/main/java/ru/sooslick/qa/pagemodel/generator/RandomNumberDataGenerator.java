package ru.sooslick.qa.pagemodel.generator;

import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.pagemodel.annotations.GeneratorName;

import java.util.Random;

/**
 * Data generator for providing random integer numbers using java's {@link Random}
 */
@NoArgsConstructor
@GeneratorName("random number")
public class RandomNumberDataGenerator implements DataGenerator {
    private static final Random RANDOM = new Random();

    @Override
    public String generate(String source, ScenarioContext context) {
        try {
            String[] parts = source.split("-", 2);
            int minInclusive = Integer.parseInt(parts[0]);
            int maxInclusive = Integer.parseInt(parts[1]);
            Assertions.assertTrue(maxInclusive > minInclusive);
            return String.valueOf(RANDOM.nextInt(minInclusive, maxInclusive + 1));
        } catch (Throwable e) {
            throw new IllegalArgumentException(String.format("Wrong range format: '%s'. Range must match the regex ([0-9]+)-([0-9]+)", source), e);
        }
    }
}
