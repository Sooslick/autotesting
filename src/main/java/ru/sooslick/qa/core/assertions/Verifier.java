package ru.sooslick.qa.core.assertions;

public interface Verifier<DataType> {

    void test(DataType actualValue);
}
