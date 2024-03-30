package ru.sooslick.qa.core.repeaters;

import org.jetbrains.annotations.Nullable;

/**
 * Interface for grouping and repeating steps
 */
public interface Repeatable {

    /**
     * Run steps and return if success or not, and stores failure for unsuccessful attempts.
     *
     * @return true if steps are successfully completed, false otherwise.
     */
    boolean runSteps();

    /**
     * @return Returns stored failures if {@link Repeatable#runSteps} was failed, or null otherwise.
     */
    @Nullable Throwable getFailure();
}
