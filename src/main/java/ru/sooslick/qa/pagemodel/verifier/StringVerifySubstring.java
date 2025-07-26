package ru.sooslick.qa.pagemodel.verifier;

import ru.sooslick.qa.pagemodel.annotations.StringVerifierName;

/**
 * String verifier method, that checks if actual value contains expected one
 */
@StringVerifierName("substring")
public class StringVerifySubstring implements StringVerifierMethod {
    @Override
    public boolean test(String expected, String actual) {
        if (expected == null)
            return true;
        if (actual == null)
            return false;
        return actual.contains(expected);
    }
}
