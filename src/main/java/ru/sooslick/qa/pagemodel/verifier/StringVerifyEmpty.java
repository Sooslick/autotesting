package ru.sooslick.qa.pagemodel.verifier;

import org.junit.platform.commons.util.StringUtils;
import ru.sooslick.qa.pagemodel.annotations.StringVerifierName;

/**
 * String verifier method, that checks if actual value is blank (null or zero length)
 */
@StringVerifierName("empty")
public class StringVerifyEmpty implements StringVerifierMethod {
    @Override
    public boolean test(String expected, String actual) {
        return StringUtils.isBlank(actual);
    }
}
