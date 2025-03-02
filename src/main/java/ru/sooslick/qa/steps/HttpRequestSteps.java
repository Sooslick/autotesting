package ru.sooslick.qa.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.assertions.HttpResponseVerifier;
import ru.sooslick.qa.core.helper.DataGeneratorsHelper;
import ru.sooslick.qa.pagemodel.annotations.Context;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * todo javadoc, review steps & methods
 */
public class HttpRequestSteps {

    @Context
    ScenarioContext context;

    private final OkHttpClient client = new OkHttpClient();
    private final List<Response> connections = new LinkedList<>();

    @SneakyThrows
    @Given("A user sends a HTTP-request to {dataGenerator} and writes the response to variable {string}")
    public void sendRequest(String url, String variable, DataTable properties) {
        // todo headers
        // todo method + body
        String finalUrl = buildUrl(url, properties);
        Request.Builder rb = new Request.Builder()
                .url(finalUrl)
                .get();
        Response response = client.newCall(rb.build()).execute();
        connections.add(response);
        context.saveVariable(variable, response);
    }

    @Then("HTTP response from variable {variable} has following properties")
    public void verifyResponse(Object probablyResponse, DataTable checks) {
        if (probablyResponse == null)
            throw new IllegalArgumentException("Variable has not defined during test execution");
        if (probablyResponse instanceof Response response) {
            new HttpResponseVerifier(response, checks, context).test();
        } else {
            throw new IllegalArgumentException("Variable has no HTTP response");
        }
    }

    private String buildUrl(String url, DataTable properties) {
        String query = properties.asLists().stream()
                .filter(l -> "query parameter".equals(l.get(0)))
                .map(l -> DataGeneratorsHelper.processString(l.get(1), context))
                .collect(Collectors.joining("&"));
        if (query.length() == 0)
            return url;
        else
            return url + "?" + query;
    }

    @After
    private void rollback() {
        connections.forEach(Response::close);
    }
}
