package ru.sooslick.qa.steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import lombok.SneakyThrows;
import ru.sooslick.qa.core.RunnerProperties;
import ru.sooslick.qa.core.repeaters.Repeat;

import java.util.concurrent.TimeUnit;

public class WaitSteps {

    @SneakyThrows
    @Given("A user waits {long} seconds")
    public void justWait(long seconds) {
        TimeUnit.SECONDS.sleep(seconds);
    }

    @Given("A user sets a timeout of {int} seconds for implicit waits")
    public void setImplicitTimeout(int duration) {
        Repeat.REPEAT_DURATION = duration * 1000L;
    }

    @After
    public void rollbackState() {
        Repeat.REPEAT_DURATION = RunnerProperties.REPEAT_DURATION;
    }
}
