package ru.sooslick.qa.core.repeaters;

/**
 * Interface for grouping and repeating steps
 */
public interface Repeatable<T> {

    /**
     * Run associated action and return result after first successful execution.
     * Method will throw an exception if no successful executions were completed before repeat timeout.
     *
     * @return result of first successful execution
     * @throws Throwable summary of all failed executions if no successful executions were completed before repeat timeout.
     */
    T repeat() throws Throwable;
}
