package ru.sooslick.qa.pagemodel.precondition;

import ru.sooslick.qa.pagemodel.annotations.PreconditionName;

import java.util.Map;

/**
 * Interface for various preconditions to cucumber scenarios.
 * Primary goal of preconditions is to execute arbitrary java code, mainly for creating various test data,
 * using parameters given, and delete all generated stuff after test is completed (nor successful neither not).
 * <p>
 * To register precondition for usage in cucumber scenarios, implementation class must have {@link PreconditionName} annotation.
 */
public interface Precondition {

    /**
     * Executes precondition.
     */
    void complete();

    /**
     * Cleans up any stuff created by this precondition.
     */
    default void rollback() {
    }

    /**
     * Sets up precondition parameters before completing
     * (since there are no way to pass any parameters directly to {@link Precondition#complete} method).
     *
     * @param params precondition's parameters map.
     */
    default void withParameters(Map<String, String> params) {
    }
}
