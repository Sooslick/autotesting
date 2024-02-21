package ru.sooslick.qa.core.repeaters;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import ru.sooslick.qa.core.helper.ExceptionsHelper;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class ParametrizedRepeatable<Entity> implements Repeatable {
    private final Entity entity;
    private final Consumer<Entity> steps;
    private final LinkedList<Throwable> failures = new LinkedList<>();

    public boolean runSteps() {
        try {
            steps.accept(entity);
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
