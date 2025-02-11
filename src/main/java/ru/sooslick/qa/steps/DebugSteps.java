package ru.sooslick.qa.steps;

import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DebugSteps {

    @Given("A user prints {string} to System.out")
    public void print(String value) {
        log.info(value);
    }
}
