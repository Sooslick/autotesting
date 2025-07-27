package ru.sooslick.qa.core.assertions;

import kotlin.Pair;
import lombok.Getter;
import org.junit.jupiter.api.AssertionFailureBuilder;
import ru.sooslick.qa.pagemodel.verifier.StringVerifierMethod;
import ru.sooslick.qa.pagemodel.verifier.StringVerifiers;
import ru.sooslick.qa.pagemodel.verifier.StringVerifyEquals;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Verifier class for comparing text data using various comparison methods.
 */
public class StringVerifier implements Verifier {
    private final Pattern BRACKETS_PATTERN = Pattern.compile("\\[(.*?)]");
    private final StringVerifierMethod DEFAULT_METHOD = new StringVerifyEquals();

    @Getter
    private final String expectedValue;
    private final StringVerifierMethod method;

    private boolean expectedTestResult = true;

    /**
     * Default constructor with reference string.
     *
     * @param expectedValueTemplate template string with comparison method.
     *                              Default comparison method is strict check for equality, case-sensitive,
     *                              or specified inside square brackets.
     *                              Example: for string "[substring] expected value" specified comparison method "substring",
     *                              and test method will check if actual string contains substring "expected value".
     */
    public StringVerifier(String expectedValueTemplate) {
        Pair<StringVerifierMethod, String> kv = extractBrackets(expectedValueTemplate);
        this.method = kv.getFirst();
        this.expectedValue = kv.getSecond();
    }

    /**
     * Applies logical not to verifier expression
     *
     * @return this
     */
    // todo explore possibility to use [brackets] expr instead of duplicating steps
    public StringVerifier not() {
        this.expectedTestResult = false;
        return this;
    }

    /**
     * Compares actual value with expected template using selected comparison method.
     * Method throws AssertionError if check fails.
     *
     * @param actualValue string for check.
     */
    public void test(String actualValue) {
        if (get(actualValue) != expectedTestResult)
            fail(actualValue);
    }

    /**
     * Performs a check, that at least one of provided values passes a test using selected comparison method.
     * Method throws AssertionError if check fails.
     *
     * @param actualValues collections of string to check
     */
    public void testAny(Collection<String> actualValues) {
        if (actualValues == null || actualValues.isEmpty()) {
            fail(actualValues);
            return;
        }
        if (actualValues.stream().noneMatch(av -> get(av) == expectedTestResult))
            fail(actualValues);
    }

    /**
     * Performs a check, that all provided values passes a test using selected comparison method.
     * Method throws AssertionError if check fails.
     *
     * @param actualValues collections of string to check
     */
    public void testAll(Collection<String> actualValues) {
        if (actualValues == null || actualValues.isEmpty()) {
            fail(actualValues);
            return;
        }
        if (actualValues.stream().noneMatch(av -> get(av) != expectedTestResult))
            fail(actualValues);
    }

    /**
     * Compares actual value with expected template using selected comparison method and returns comparison result
     *
     * @param actualValue string for check.
     * @return comparison result
     */
    public boolean get(String actualValue) {
        return method.test(expectedValue, actualValue);
    }

    // todo similar method used for extracting data generators, probably I should put aside these methods
    private Pair<StringVerifierMethod, String> extractBrackets(String source) {
        Matcher m = BRACKETS_PATTERN.matcher(source);
        if (m.find()) {
            String methodName = m.group(1);
            StringVerifierMethod method = StringVerifiers.getMethod(methodName);
            return new Pair<>(method, m.replaceFirst("").trim());
        }
        return new Pair<>(DEFAULT_METHOD, source);
    }

    private void fail(Object actual) {
        AssertionFailureBuilder.assertionFailure()
                .expected(expectedValue)
                .actual(actual)
                .buildAndThrow();
    }
}
