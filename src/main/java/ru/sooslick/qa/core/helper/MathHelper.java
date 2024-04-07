package ru.sooslick.qa.core.helper;

import lombok.experimental.UtilityClass;
import ru.sooslick.qa.core.RunnerProperties;

/**
 * Utility class with math operations to make easy references in lambda expressions.
 */
@UtilityClass
public class MathHelper {

    public final double DELTA = RunnerProperties.DELTA;

    public boolean equals(double expected, double actual) {
        return Math.abs(expected - actual) <= DELTA;
    }

    public boolean notEquals(double expected, double actual) {
        return Math.abs(expected - actual) > DELTA;
    }

    public boolean biggerThan(double expected, double actual) {
        return actual > expected;
    }

    public boolean equalsOrBiggerThan(double expected, double actual) {
        return actual >= expected;
    }

    public boolean lesserThan(double expected, double actual) {
        return actual < expected;
    }

    public boolean equalsOrLesserThan(double expected, double actual) {
        return actual <= expected;
    }
}
