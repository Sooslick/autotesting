package ru.sooslick.qa.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AssertionFailureBuilder;
import ru.sooslick.qa.core.helper.MathHelper;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public enum NumberComparisonMethod {
    EQUALS("equals to", MathHelper::equals),
    NOT_EQUALS("not equals to", MathHelper::notEquals),
    EQUALS_OR_BIGGER_THAN("equals or bigger than", MathHelper::equalsOrBiggerThan),
    BIGGER_THAN("bigger than", MathHelper::biggerThan),
    EQUALS_OR_LESSER_THAN("equals or lesser than", MathHelper::equalsOrLesserThan),
    LESSER_THAN("lesser than", MathHelper::lesserThan);

    @Getter
    private final String word;
    private final BiFunction<Double, Double, Boolean> comparsion;

    public void test(double expected, double actual) {
        if (!comparsion.apply(expected, actual)) {
            AssertionFailureBuilder.assertionFailure()
                    .expected(expected)
                    .actual(actual)
                    .buildAndThrow();
        }
    }
}
