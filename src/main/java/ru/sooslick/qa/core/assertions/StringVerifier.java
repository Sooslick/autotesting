package ru.sooslick.qa.core.assertions;

import org.junit.jupiter.api.AssertionFailureBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Verifier class for strings with various verifying methods.
 */
public class StringVerifier implements Verifier {
    private final Pattern BRACKETS_PATTERN = Pattern.compile("\\[(.*?)]");
    private final Map<String, BiPredicate<String, String>> VERIFIER_METHODS = new HashMap<>() {{
        // todo probably I should use Interface + implementations system like I did with actions / generators
        put("substring", (expected, actual) -> actual.contains(expected));
        put("regexp", (expected, actual) -> actual.matches(expected));
    }};

    private final String expectedValue;
    private BiPredicate<String, String> method = String::equals;

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
        String processString = expectedValueTemplate;
        if (expectedValueTemplate.startsWith("["))
            processString = extractBrackets(processString);
        this.expectedValue = processString;
    }

    /**
     * Compares actual value with expected template using selected comparison method.
     * Method throws AssertionError if check fails.
     *
     * @param actualValue string for check.
     */
    public void test(String actualValue) {
        if (!get(actualValue))
            AssertionFailureBuilder.assertionFailure()
                    .expected(expectedValue)
                    .actual(actualValue)
                    .buildAndThrow();
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
    private String extractBrackets(String source) {
        Matcher m = BRACKETS_PATTERN.matcher(source);
        if (m.find()) {
            String methodName = m.group(1);
            var probablyMethod = VERIFIER_METHODS.get(methodName.toLowerCase());
            if (probablyMethod != null) {
                method = probablyMethod;
                return m.replaceFirst("").trim();
            }
        }
        return source;
    }
}
