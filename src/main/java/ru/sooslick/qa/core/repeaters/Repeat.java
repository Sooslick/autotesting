package ru.sooslick.qa.core.repeaters;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import ru.sooslick.qa.core.RunnerProperties;
import ru.sooslick.qa.core.helper.ExceptionsHelper;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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
     * Repeater will reattempt to run steps until {@link Repeatable#runSteps} method returns true
     * at least MIN_ATTEMPTS times and at least REPEAT_DURATION milliseconds,
     * and then returns, or in throws MultipleFailureException with entire history of failures in unsuccessful case.
     *
     * @param steps group of steps to repeat.
     */
    public void untilSuccess(Runnable steps) {  // Todo - cannot throw exception using runnable, use another interface
        int iteration = 0;
        long startTime = System.currentTimeMillis();
        Repeatable repeater = new RepeatableAction(steps);
        while (!repeater.runSteps()) {
            if (++iteration >= MIN_ATTEMPTS && System.currentTimeMillis() >= startTime + REPEAT_DURATION)
                break;
        }
        // todo refactor throwing methods
        collectErrors(repeater);
    }

    /**
     * Repeat group of steps for each element of collection to successful result or timeout.
     * Repeater will reattempt to run steps until {@link Repeatable#runSteps} method returns true
     * at least MIN_ATTEMPTS times and at least REPEAT_DURATION milliseconds for each element of collection,
     * and then returns, or in throws MultipleFailureException with entire history of failures in unsuccessful case.
     *
     * @param entities parameters collection to run parametrized steps.
     * @param steps    group of steps to repeat.
     */
    public <T> void forEachUntilSuccess(Collection<T> entities, Consumer<T> steps) {
        int iteration = 0;
        long startTime = System.currentTimeMillis();
        List<ParametrizedRepeatable<T>> repeaters = createRepeaters(entities, steps);
        do {
            repeaters.removeAll(repeaters.stream()
                    .filter(ParametrizedRepeatable::runSteps)
                    .collect(Collectors.toList()));
            if (++iteration >= MIN_ATTEMPTS && System.currentTimeMillis() >= startTime + REPEAT_DURATION)
                break;
        } while (!repeaters.isEmpty());
        collectErrors(repeaters);
    }

    private <T> List<ParametrizedRepeatable<T>> createRepeaters(Collection<T> entities, Consumer<T> steps) {
        return entities.stream()
                .map(e -> new ParametrizedRepeatable<>(e, steps))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    private void collectErrors(Repeatable repeater) {
        Throwable failure = repeater.getFailure();
        if (failure != null)
            throw failure;
    }

    @SneakyThrows
    private <T> void collectErrors(Collection<ParametrizedRepeatable<T>> repeaters) {
        Throwable failure = ExceptionsHelper.convertExceptionList(repeaters.stream()
                .map(ParametrizedRepeatable::getFailure)
                .collect(Collectors.toList()));
        if (failure != null)
            throw failure;
    }
}
