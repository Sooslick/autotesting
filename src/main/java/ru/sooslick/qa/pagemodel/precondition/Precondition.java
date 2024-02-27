package ru.sooslick.qa.pagemodel.precondition;

import java.util.Map;

public interface Precondition {

    void complete();

    default void rollback() {
    }

    default void withParameters(Map<String, String> params) {
    }
}
