package ru.sooslick.qa.core;

import lombok.extern.slf4j.Slf4j;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;

/**
 * Dummy Junit5 test listener with single purpose to log test execution results.
 */
@Slf4j
public class Slf4jTestListener implements TestExecutionListener {

    @Override
    public void executionStarted(TestIdentifier testIdentifier) {
        log.info("Started {} {}", testIdentifier.getType(), testIdentifier.getDisplayName());
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        log.info("Finished {} {} with status {}", testIdentifier.getType(), testIdentifier.getDisplayName(), testExecutionResult.getStatus());
        if (testExecutionResult.getStatus().equals(TestExecutionResult.Status.FAILED))
            log.warn("Failure!", testExecutionResult.getThrowable().get());
    }
}
