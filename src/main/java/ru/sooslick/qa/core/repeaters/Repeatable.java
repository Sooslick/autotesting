package ru.sooslick.qa.core.repeaters;

import org.jetbrains.annotations.Nullable;

public interface Repeatable {

    boolean runSteps();

    @Nullable Throwable getFailure();
}
