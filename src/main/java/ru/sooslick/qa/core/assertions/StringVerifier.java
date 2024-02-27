package ru.sooslick.qa.core.assertions;

import org.junit.jupiter.api.AssertionFailureBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringVerifier implements Verifier<String> {
    private final Pattern BRACKETS_PATTERN = Pattern.compile("\\[(.*?)]");
    private final Map<String, BiPredicate<String, String>> VERIFIER_METHODS = new HashMap<>() {{
        // todo probably I should use Interface + implementations system like I did with actions / generators
        put("substring", (expected, actual) -> actual.contains(expected));
    }};

    private final String expectedValue;
    private BiPredicate<String, String> method = String::equals;

    public StringVerifier(String expectedValueTemplate) {
        String processString = expectedValueTemplate;
        if (expectedValueTemplate.startsWith("["))
            processString = extractBrackets(processString);
        this.expectedValue = processString;
    }

    @Override
    public void test(String actualValue) {
        if (!method.test(expectedValue, actualValue))
            AssertionFailureBuilder.assertionFailure()
                    .expected(expectedValue)
                    .actual(actualValue)
                    .buildAndThrow();
    }

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
