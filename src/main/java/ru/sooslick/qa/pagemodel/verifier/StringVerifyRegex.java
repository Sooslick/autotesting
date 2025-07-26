package ru.sooslick.qa.pagemodel.verifier;

import ru.sooslick.qa.pagemodel.annotations.StringVerifierName;

/**
 * String verifier method, that checks if actual value matches expected regular expression
 */
@StringVerifierName("regexp")
public class StringVerifyRegex implements StringVerifierMethod {
    @Override
    public boolean test(String expected, String actual) {
        if (expected == null || actual == null)
            return false;
        return actual.matches(expected);
    }
}
