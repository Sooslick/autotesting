package ru.sooslick.qa.pagemodel.verifier;

import org.apache.commons.lang3.StringUtils;
import ru.sooslick.qa.pagemodel.annotations.StringVerifierName;

import java.util.Objects;

/**
 * Default string verifier method, that compares two strings with normalized whitespaces (case-sensitive)
 */
@StringVerifierName("normalize space")
public class StringVerifyNormalizeSpace implements StringVerifierMethod {
    @Override
    public boolean test(String expected, String actual) {
        return Objects.equals(StringUtils.normalizeSpace(expected), StringUtils.normalizeSpace(actual));
    }
}
