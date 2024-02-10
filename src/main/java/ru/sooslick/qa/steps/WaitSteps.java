package ru.sooslick.qa.steps;

import io.cucumber.java.en.Given;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class WaitSteps {

    @SneakyThrows
    @Given("A user waits {long} seconds")
    public void justWait(long seconds) {
        TimeUnit.SECONDS.sleep(seconds);
    }
}
