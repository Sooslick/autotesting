package ru.sooslick.qa.core.helper;

import lombok.experimental.UtilityClass;

/**
 * Utility class with math operations to make easy references in lambda expressions.
 */
@UtilityClass
public class MathHelper {

    /**
     * Max allowed margin of error for floating point numbers.
     */
    //todo config
    public final double DELTA = 0.001d;

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
