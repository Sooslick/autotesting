package ru.sooslick.qa.steps;

import lombok.SneakyThrows;
import ru.sooslick.qa.core.Repeater;
import ru.sooslick.qa.core.helper.ExceptionsHelper;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

// todo Utility Class?
public class RepeatSteps {
    // todo config
    public static final int MIN_ATTEMPTS = 2;
    public static final long REPEAT_DURATION = 5000;

    public static <T> void untilSuccess(T entity, Consumer<T> steps) {
        int iteration = 0;
        long startTime = System.currentTimeMillis();
        Repeater<T> repeater = new Repeater<>(entity, steps);
        while (!repeater.runSteps()) {
            if (++iteration >= MIN_ATTEMPTS && System.currentTimeMillis() >= startTime + REPEAT_DURATION)
                break;
        }
        collectErrors(repeater);
    }

    public static <T> void forEachUntilSuccess(Collection<T> entities, Consumer<T> steps) {
        int iteration = 0;
        long startTime = System.currentTimeMillis();
        List<Repeater<T>> repeaters = createRepeaters(entities, steps);
        do {
            repeaters.removeAll(repeaters.stream()
                    .filter(Repeater::runSteps)
                    .collect(Collectors.toList()));
            if (++iteration >= MIN_ATTEMPTS && System.currentTimeMillis() >= startTime + REPEAT_DURATION)
                break;
        } while (!repeaters.isEmpty());
        collectErrors(repeaters);
    }

    private static <T> List<Repeater<T>> createRepeaters(Collection<T> entities, Consumer<T> steps) {
        return entities.stream()
                .map(e -> new Repeater<>(e, steps))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    private static <T> void collectErrors(Repeater<T> repeater) {
        Throwable failure = repeater.getFailure();
        if (failure != null)
            throw failure;
    }

    @SneakyThrows
    private static <T> void collectErrors(Collection<Repeater<T>> repeaters) {
        Throwable failure = ExceptionsHelper.convertExceptionList(repeaters.stream()
                .map(Repeater::getFailure)
                .collect(Collectors.toList()));
        if (failure != null)
            throw failure;
    }
}
