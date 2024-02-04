package ru.sooslick.qa.steps;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.opentest4j.MultipleFailuresError;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

// todo Utility Class?
public class RepeatSteps {
    // todo config
    public static final int MIN_ATTEMPTS = 2;
    public static final long REPEAT_DURATION = 5000;

    @SneakyThrows
    public static <T> void forEachUntilSuccess(Collection<T> entities, Consumer<T> steps) {
        int iteration = 0;
        long startTime = System.currentTimeMillis();
        List<Repeater<T>> repeaters = entities.stream()
                .map(e -> new Repeater<>(e, steps))
                .collect(Collectors.toList());
        do {
            repeaters.removeAll(repeaters.stream()
                    .filter(Repeater::runSteps)
                    .collect(Collectors.toList()));
            if (++iteration >= MIN_ATTEMPTS && System.currentTimeMillis() >= startTime + REPEAT_DURATION)
                break;
        } while (!repeaters.isEmpty());
        List<Throwable> failures = repeaters.stream()
                .map(Repeater::getFailure)
                .collect(Collectors.toList());
        // todo refactor code and duplications
        if (failures.isEmpty())
            return;
        if (failures.size() == 1)
            throw failures.get(0);
        throw new MultipleFailuresError(null, failures);
    }

    @RequiredArgsConstructor
    private static class Repeater<Entity> {
        private final Entity entity;
        private final Consumer<Entity> steps;
        private final LinkedList<Throwable> failures = new LinkedList<>();

        public boolean runSteps() {
            try {
                steps.accept(entity);
                return true;
            } catch (Exception e) {
                failures.add(e);
            }
            return false;
        }

        public Throwable getFailure() {
            List<Throwable> filteredExceptions = filterExceptions();
            if (filteredExceptions.isEmpty())
                return null;
            if (filteredExceptions.size() == 1)
                return filteredExceptions.get(0);
            return new MultipleFailuresError(null, filteredExceptions);
        }

        private List<Throwable> filterExceptions() {
            if (failures.isEmpty())
                return Collections.emptyList();

            List<Throwable> filteredExceptions = new LinkedList<>();
            Throwable lastCheckedThrowable = failures.get(0);
            filteredExceptions.add(lastCheckedThrowable);
            for (Throwable f : failures) {
                // todo I can simplify this code but I am too lazy to do it now
                if (f.getClass().equals(lastCheckedThrowable.getClass())) {
                    if (!f.getMessage().equals(lastCheckedThrowable.getMessage())) {
                        lastCheckedThrowable = f;
                        filteredExceptions.add(lastCheckedThrowable);
                    }
                } else {
                    lastCheckedThrowable = f;
                    filteredExceptions.add(lastCheckedThrowable);
                }
            }
            return filteredExceptions;
        }
    }
}
