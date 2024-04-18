package ru.sooslick.qa.core;

import lombok.AllArgsConstructor;
import ru.sooslick.qa.core.assertions.SortingVerifier;

import java.util.Comparator;
import java.util.function.Function;

// todo javadoc
@AllArgsConstructor
public enum SortingType {

    // todo I SHOULD create own classes for sortings and implement something like Data Generators

    STRINGS(
            (v) -> v,
            (Comparator<String>) CharSequence::compare
    ),
    NUMBERS(
            Double::parseDouble,
            (Comparator<Double>) Double::compare
    );

    private final Function<String, Object> mapper;
    private final Comparator comparator;

    public SortingVerifier create() {
        return new SortingVerifier()
                .mapFunction(mapper)
                .comparator(comparator);
    }
}
