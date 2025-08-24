package ru.sooslick.qa.core.repeaters;

import lombok.experimental.UtilityClass;
import ru.sooslick.qa.core.RunnerProperties;

import java.util.Collection;
import java.util.function.Consumer;

/**
 * Utility class for repeating actions.
 */
@UtilityClass
public class Repeat {

    /**
     * Min amount of attempts to run group of steps.
     */
    public final int MIN_ATTEMPTS = RunnerProperties.REPEAT_MIN_ATTEMPTS;

    /**
     * Min duration of reattempting to run steps in milliseconds.
     */
    public long REPEAT_DURATION = RunnerProperties.REPEAT_DURATION;

    /**
     * Repeat group of steps to successful result or timeout.
     * Repeater will reattempt to run steps until {@link Repeatable#repeat} method returns true
     * at least MIN_ATTEMPTS times and at least REPEAT_DURATION milliseconds,
     * and then returns, or in throws MultipleFailureException with entire history of failures in unsuccessful case.
     *
     * @param steps group of steps to repeat.
     */
    public void untilSuccess(Runnable steps) {
        new RepeatableAction(steps)
                .minAttempts(MIN_ATTEMPTS)
                .repeatDuration(REPEAT_DURATION)
                .repeat();
    }

    /**
     * Repeat group of steps for each element of collection to successful result or timeout.
     * Repeater will reattempt to run steps until {@link Repeatable#repeat} method returns true
     * at least MIN_ATTEMPTS times and at least REPEAT_DURATION milliseconds for each element of collection,
     * and then returns, or in throws MultipleFailureException with entire history of failures in unsuccessful case.
     *
     * @param entities parameters collection to run parametrized steps.
     * @param steps    group of steps to repeat.
     */
    public <T> void forEachUntilSuccess(Collection<T> entities, Consumer<T> steps) {
        new RepeatableGroup<>(entities, steps)
                .minAttempts(MIN_ATTEMPTS)
                .repeatDuration(REPEAT_DURATION)
                .repeat();
    }
}
