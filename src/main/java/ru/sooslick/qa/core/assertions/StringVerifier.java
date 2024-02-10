package ru.sooslick.qa.core.assertions;

import org.junit.jupiter.api.Assertions;

public class StringVerifier implements Verifier<String> {
    String expectedValue;

    public StringVerifier(String expectedValueTemplate) {
        // todo Implement comparator types (like substring, capslock, regexp, etc...)
        this.expectedValue = expectedValueTemplate;
    }

    @Override
    public void test(String actualValue) {
        Assertions.assertEquals(expectedValue, actualValue);
    }
}
