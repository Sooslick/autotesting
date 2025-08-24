package ru.sooslick.qa.core.repeaters;

import lombok.SneakyThrows;
import ru.sooslick.qa.core.RunnerProperties;
import ru.sooslick.qa.core.helper.ExceptionsHelper;

import java.util.LinkedList;

/**
 * Abstract class with Repeatable implementation.
 * AbstractRepeatable declare abstract doAction method that should implement some kind of steps that may fail,
 * but eventually will return the result successfully
 *
 * @param <T> execution result type
 */
public abstract class AbstractRepeatable<T> implements Repeatable<T> {
    public int minAttempts = RunnerProperties.REPEAT_MIN_ATTEMPTS;
    public long repeatDuration = RunnerProperties.REPEAT_DURATION;

    @Override
    @SneakyThrows
    public T repeat() {
        LinkedList<Throwable> failures = new LinkedList<>();
        int iteration = 0;
        long endTime = System.currentTimeMillis() + repeatDuration;
        while (System.currentTimeMillis() <= endTime || iteration++ <= minAttempts) {
            try {
                return doAction();
            } catch (Throwable t) {
                failures.add(t);
            }
        }
        String summary = "Unable to perform repeats with success, failed attempts count: " + iteration;
        throw ExceptionsHelper.convertExceptionList(ExceptionsHelper.reduceDuplications(failures), summary);
    }

    /**
     * Method that will execute action within repeat()
     *
     * @return execution result
     * @throws Throwable any exception that occurs during execution
     */
    public abstract T doAction() throws Throwable;

    /**
     * Set minimal required attempts count
     *
     * @return this
     */
    public <R extends AbstractRepeatable<T>> R minAttempts(int minAttempts) {
        this.minAttempts = minAttempts;
        return (R) this;
    }

    /**
     * Set minimal time required
     *
     * @return this
     */
    public <R extends AbstractRepeatable<T>> R repeatDuration(long repeatDuration) {
        this.repeatDuration = repeatDuration;
        return (R) this;
    }
}
