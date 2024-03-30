package ru.sooslick.qa.core.repeaters;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import ru.sooslick.qa.core.helper.ExceptionsHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Default repeatable implementation for single group of steps.
 */
@RequiredArgsConstructor
public class RepeatableAction implements Repeatable {
    private final Runnable steps;
    private final LinkedList<Throwable> failures = new LinkedList<>();
    private boolean succeed = false;

    @Override
    public boolean runSteps() {
        try {
            steps.run();
            succeed = true;
            return true;
        } catch (Throwable e) {
            failures.add(e);
        }
        return false;
    }

    @Override
    public @Nullable Throwable getFailure() {
        if (succeed)
            return null;
        List<Throwable> filteredExceptions = ExceptionsHelper.reduceDuplications(failures);
        return ExceptionsHelper.convertExceptionList(filteredExceptions);
    }
}
