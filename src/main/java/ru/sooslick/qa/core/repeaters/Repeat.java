package ru.sooslick.qa.core.repeaters;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import ru.sooslick.qa.core.helper.ExceptionsHelper;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@UtilityClass
public class Repeat {
    // todo config
    public final int MIN_ATTEMPTS = 2;
    public final long REPEAT_DURATION = 5000;

    public void untilSuccess(Runnable steps) {
        int iteration = 0;
        long startTime = System.currentTimeMillis();
        Repeatable repeater = new RepeatableAction(steps);
        while (!repeater.runSteps()) {
            if (++iteration >= MIN_ATTEMPTS && System.currentTimeMillis() >= startTime + REPEAT_DURATION)
                break;
        }
        collectErrors(repeater);
    }

    public <T> void forEachUntilSuccess(Collection<T> entities, Consumer<T> steps) {
        int iteration = 0;
        long startTime = System.currentTimeMillis();
        List<ParametrizedRepeatable<T>> repeaters = createRepeaters(entities, steps);
        do {
            repeaters.removeAll(repeaters.stream()
                    .filter(ParametrizedRepeatable::runSteps)
                    .collect(Collectors.toList()));
            if (++iteration >= MIN_ATTEMPTS && System.currentTimeMillis() >= startTime + REPEAT_DURATION)
                break;
        } while (!repeaters.isEmpty());
        collectErrors(repeaters);
    }

    private <T> List<ParametrizedRepeatable<T>> createRepeaters(Collection<T> entities, Consumer<T> steps) {
        return entities.stream()
                .map(e -> new ParametrizedRepeatable<>(e, steps))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    private void collectErrors(Repeatable repeater) {
        Throwable failure = repeater.getFailure();
        if (failure != null)
            throw failure;
    }

    @SneakyThrows
    private <T> void collectErrors(Collection<ParametrizedRepeatable<T>> repeaters) {
        Throwable failure = ExceptionsHelper.convertExceptionList(repeaters.stream()
                .map(ParametrizedRepeatable::getFailure)
                .collect(Collectors.toList()));
        if (failure != null)
            throw failure;
    }
}
