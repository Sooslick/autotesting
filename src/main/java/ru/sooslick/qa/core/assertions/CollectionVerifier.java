package ru.sooslick.qa.core.assertions;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AssertionFailureBuilder;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.function.BiPredicate;

/**
 * Assertions for various collections
 *
 * @param <ElementType> content type of given collection
 */
@RequiredArgsConstructor
@Accessors(fluent = true)
public class CollectionVerifier<ElementType> implements Verifier {

    private final Collection<ElementType> expectedValues;

    @Setter
    private BiPredicate<ElementType, ElementType> compareFunction = Objects::equals;

    /**
     * Performs a check that given collection strictly equals to expected template.
     *
     * @param actualValues collection to test
     */
    public void testStrict(@NotNull Collection<ElementType> actualValues) {
        if (expectedValues.size() != actualValues.size())
            fail("Different items count, expected " + expectedValues.size() + ", actual " + actualValues.size());
        Iterator<ElementType> expectedIterator = expectedValues.iterator();
        Iterator<ElementType> actualIterator = actualValues.iterator();
        int index = 0;
        LinkedList<String> failures = new LinkedList<>();
        while (expectedIterator.hasNext()) {
            index++;
            ElementType expected = expectedIterator.next();
            ElementType actual = actualIterator.next();
            if (!compareFunction.test(expected, actual))
                failures.add(formatCompare(expected, actual, index));
        }
        if (failures.size() > 0)
            fail(String.join("\n", failures));
    }

    /**
     * Performs a check that given collection contains all values from expected template
     *
     * @param actualValues collection to test
     */
    public void testContains(@NotNull Collection<ElementType> actualValues) {
        LinkedList<ElementType> itemsToFind = new LinkedList<>(expectedValues);
        for (ElementType actual : actualValues) {
            for (ElementType expected : itemsToFind) {
                if (compareFunction.test(expected, actual)) {
                    itemsToFind.remove(expected);
                    break;
                }
            }
        }
        if (itemsToFind.size() > 0) {
            fail("List does not contain following items: " + itemsToFind);
        }
    }

    private void fail(String causeMessage) {
        AssertionFailureBuilder.assertionFailure()
                .message("List does not match")
                .reason(causeMessage)
                .buildAndThrow();
    }

    private String formatCompare(ElementType a, ElementType b, int index) {
        return "List item does not match at index %d%nExpected: %s%n  Actual: %s%n".formatted(index, a, b);
    }
}
