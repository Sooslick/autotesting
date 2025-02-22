package ru.sooslick.qa.steps.browser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.logging.LogType;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.assertions.StringVerifier;
import ru.sooslick.qa.core.helper.JsonHelper;
import ru.sooslick.qa.pagemodel.annotations.Context;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class BrowserLogsSteps {

    private static final String START_TIME_VAR = "PerfLogsStartTime";

    @Context
    private ScenarioContext context;

    @Given("A user begin watching browser logs")
    public void setStartTime() {
        context.saveVariable(START_TIME_VAR, System.currentTimeMillis());
    }

    @Then("Browser performance logs has entry with following parameters")
    public void checkPerfLog(Map<String, String> params) {
        Map<String, StringVerifier> mappedParams = params.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> new StringVerifier(e.getValue())));
        long startTime = (long) context.getVariable(START_TIME_VAR, context.getTestStartTime());

        List<JsonNode> filteredLogs = context.getWebDriver().manage().logs()
                .get(LogType.PERFORMANCE)
                .getAll()
                .stream()
                .filter(logEntry -> logEntry.getTimestamp() > startTime)
                .map(logEntry -> {
                    try {
                        return JsonHelper.OBJECT_MAPPER.readTree(logEntry.getMessage());
                    } catch (JsonProcessingException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        log.info("Analyzing {} log entries", filteredLogs.size());
        for (var e : mappedParams.entrySet()) {
            String path = e.getKey();
            StringVerifier verif = e.getValue();
            filteredLogs = filteredLogs.stream()
                    .filter(log -> verif.get(log.at(path).asText()))
                    .collect(Collectors.toList());
            log.info("Applied filter {} = {}, filtered {} logs", path, verif.getExpectedValue(), filteredLogs.size());
        }
        Assertions.assertTrue(filteredLogs.size() > 0, "Browser has no logs with given properties");
    }
}
