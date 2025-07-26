package ru.sooslick.qa.steps.browser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chromium.HasCdp;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.assertions.StringVerifier;
import ru.sooslick.qa.core.helper.JsonHelper;
import ru.sooslick.qa.core.repeaters.Repeat;
import ru.sooslick.qa.pagemodel.annotations.Context;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class BrowserLogsSteps {

    @Context
    private ScenarioContext context;

    private long startWatchTime = 0;
    private List<LogEntry> perflogs;

    @Given("A user begin watching browser logs")
    public void setStartTime() {
        startWatchTime = System.currentTimeMillis();
    }

    @Then("Browser performance logs has entry with following parameters")
    public void checkPerfLog(Map<String, String> params) {
        Map<String, StringVerifier> mappedParams = params.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> new StringVerifier(e.getValue())));

        Repeat.untilSuccess(() -> {
            List<JsonNode> logs = getPerflogs();

            log.debug("Analyzing {} log entries", logs.size());
            for (var e : mappedParams.entrySet()) {
                String path = e.getKey();
                StringVerifier verif = e.getValue();
                logs = logs.stream()
                        .filter(log -> verif.get(log.at(path).asText()))
                        .collect(Collectors.toList());
                log.debug("Applied filter {} = {}, filtered {} logs", path, verif.getExpectedValue(), logs.size());
            }
            Assertions.assertTrue(logs.size() > 0, "Browser has no logs with given properties");
        });
    }

    @Then("Browser performance logs has not entries with following parameters")
    public void checkPerfLogNNotExist(Map<String, String> params) {
        Map<String, StringVerifier> mappedParams = params.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> new StringVerifier(e.getValue())));

        Repeat.untilSuccess(() -> {
            List<JsonNode> logs = getPerflogs();

            log.info("Analyzing {} log entries", logs.size());
            for (var e : mappedParams.entrySet()) {
                String path = e.getKey();
                StringVerifier verif = e.getValue();
                logs = logs.stream()
                        .filter(log -> verif.get(log.at(path).asText()))
                        .collect(Collectors.toList());
                log.info("Applied filter {} = {}, filtered {} logs", path, verif.getExpectedValue(), logs.size());
            }
            Assertions.assertTrue(logs.isEmpty(), "Browser has logs with given properties, expected not!");
        });
    }

    @Given("A user saves response body of request {stringVerifier} as JSON to variable {string}")
    public void saveResponse(StringVerifier requestUrl, String variable) {
        WebDriver driver = context.getWebDriver();
        if (!(driver instanceof HasCdp cdp))
            throw new UnsupportedOperationException("Can't retrieve info from browser dev tools");

        String reqId = getPerflogs().stream()
                .filter(node -> "Network.responseReceived".equals(node.at("/message/method").asText()))
                .filter(node -> requestUrl.get(node.at("/message/params/response/url").asText()))
                .map(node -> node.at("/message/params/requestId").asText())
                .findFirst()
                .orElseThrow(() -> new AssertionError("No completed requests " + requestUrl + " were logged"));

        Map<String, Object> result = cdp.executeCdpCommand("Network.getResponseBody", Map.of("requestId", reqId));
        String body = result.get("body").toString();

        try {
            context.saveVariable(variable, JsonHelper.OBJECT_MAPPER.readTree(body));
        } catch (JsonProcessingException e) {
            throw new AssertionError("Response body is not valid JSON!");
        }
    }

    private List<JsonNode> getPerflogs() {
        updateCachedLogs();
        return perflogs.stream()
                .map(logEntry -> {
                    try {
                        return JsonHelper.OBJECT_MAPPER.readTree(logEntry.getMessage());
                    } catch (JsonProcessingException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private void updateCachedLogs() {
        if (perflogs == null)
            perflogs = fetchLogs();
        else
            perflogs.addAll(fetchLogs());

        perflogs = perflogs.stream()
                .filter(logEntry -> logEntry.getTimestamp() > startWatchTime)
                .collect(Collectors.toList());
    }

    private List<LogEntry> fetchLogs() {
        return context.getWebDriver().manage().logs()
                .get(LogType.PERFORMANCE)
                .getAll();
    }
}
