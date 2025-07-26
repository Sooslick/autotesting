package ru.sooslick.qa.pagemodel.verifier;

import ru.sooslick.qa.pagemodel.annotations.StringVerifierName;

import java.util.Objects;

/**
 * Default string verifier method, that compares two strings case-sensitive
 */
@StringVerifierName("equal")
public class StringVerifyEquals implements StringVerifierMethod {
    @Override
    public boolean test(String expected, String actual) {
        return Objects.equals(expected, actual);
    }
}
