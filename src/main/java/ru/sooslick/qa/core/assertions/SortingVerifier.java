package ru.sooslick.qa.core.assertions;

import lombok.Setter;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.AssertionFailureBuilder;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Verifier class for sorted collections of Strings
 */
@Setter
@Accessors(fluent = true)
public class SortingVerifier implements Verifier {

    //todo javadocs
    private Function<String, Object> mapFunction;
    private Comparator comparator;

    public void verifySorted(List<String> source, boolean ascending) {
        if (mapFunction == null)
            mapFunction = (s) -> s;
        if (comparator == null)
            comparator = (Comparator<String>) CharSequence::compare;

        if (source.size() <= 1)
            return;

        Predicate<Integer> compareResultPredicate = ascending ?
                (i) -> i <= 0 :
                (i) -> i >= 0;

        List<?> mappedSource = null;
        try {
            mappedSource = source.stream()
                    .map(s -> mapFunction.apply(s))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            AssertionFailureBuilder.assertionFailure()
                    .includeValuesInMessage(false)
                    .message("Failed to convert list of values to desired comparable type.\n" + source)
                    .buildAndThrow();
        }

        Iterator<?> valuesIterator = mappedSource.iterator();
        Object value1 = valuesIterator.next();
        while (valuesIterator.hasNext()) {
            Object value2 = valuesIterator.next();
            if (!compareResultPredicate.test(comparator.compare(value1, value2))) {
                AssertionFailureBuilder.assertionFailure()
                        .includeValuesInMessage(false)
                        .message("Values is not in order near values " + value1 + "\n" + source)
                        .buildAndThrow();
            }
            value1 = value2;
        }
    }
}
