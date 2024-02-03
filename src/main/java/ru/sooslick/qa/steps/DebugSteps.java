package ru.sooslick.qa.steps;

import io.cucumber.java.en.Given;

public class DebugSteps {

    @Given("The user prints {string} to System.out")
    public void print(String value) {
        System.out.println(value);
    }
}
