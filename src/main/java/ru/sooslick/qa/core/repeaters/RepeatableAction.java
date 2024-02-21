package ru.sooslick.qa.core.repeaters;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import ru.sooslick.qa.core.helper.ExceptionsHelper;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
public class RepeatableAction implements Repeatable {
    private final Runnable steps;
    private final LinkedList<Throwable> failures = new LinkedList<>();

    public boolean runSteps() {
        try {
            steps.run();
            return true;
        } catch (Throwable e) {
            failures.add(e);
        }
        return false;
    }

    public @Nullable Throwable getFailure() {
        List<Throwable> filteredExceptions = ExceptionsHelper.reduceDuplications(failures);
        return ExceptionsHelper.convertExceptionList(filteredExceptions);
    }
}
