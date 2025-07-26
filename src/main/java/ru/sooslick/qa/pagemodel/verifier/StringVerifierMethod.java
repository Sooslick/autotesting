package ru.sooslick.qa.pagemodel.verifier;

/**
 * Interface, describing a simple comparison of two individual strings
 */
public interface StringVerifierMethod {
    /**
     * Performs a check whether an actual string matches the expected one
     *
     * @param expected expected template
     * @param actual   actual value
     * @return true if check is ok, false otherwise
     */
    boolean test(String expected, String actual);
}
