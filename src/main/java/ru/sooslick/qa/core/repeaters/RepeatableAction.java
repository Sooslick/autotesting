package ru.sooslick.qa.core.repeaters;

import lombok.RequiredArgsConstructor;

/**
 * Default repeatable implementation for single group of steps.
 */
@RequiredArgsConstructor
public class RepeatableAction extends AbstractRepeatable<Void> {
    private final Runnable steps;

    @Override
    public Void doAction() throws Throwable {
        steps.run();
        return null;
    }
}
