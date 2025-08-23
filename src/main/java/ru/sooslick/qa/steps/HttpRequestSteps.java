package ru.sooslick.qa.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Assertions;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.assertions.HttpResponseVerifier;
import ru.sooslick.qa.core.http.HttpRequestWrapper;
import ru.sooslick.qa.core.http.HttpResponseWrapper;
import ru.sooslick.qa.pagemodel.annotations.Context;

import java.io.IOException;

/**
 * Steps for performing various HTTP requests and validating HTTP responses.
 * <p>
 * Steps commonly require a Cucumber Data Table with various attributes. Currently, following HTTP message attributes are supported:
 * - method - HTTP method
 * - query parameter - URL parameters, i.e. "key=value", or just "parameter"
 * - header - HTTP header in "key=value" format
 * - body - any content
 * - code - HTTP response code
 */
public class HttpRequestSteps {

    @Context
    ScenarioContext context;

    private final OkHttpClient client = new OkHttpClient();

    @Given("A user sends a HTTP-request to {dataGenerator} and writes the response to variable {string}")
    public void sendRequest(String url, String variable, DataTable properties) {
        Request request = HttpRequestWrapper.of(properties, context).buildRequest(url);
        try (Response response = client.newCall(request).execute()) {
            context.saveVariable(variable, new HttpResponseWrapper(
                    response.code(),
                    response.headers(),
                    response.body()
            ));
        } catch (IOException e) {
            Assertions.fail("Failed HTTP request to target url", e);
        }
    }

    @Then("HTTP response from variable {variable} has following properties")
    public void verifyResponse(Object probablyResponse, DataTable checks) {
        if (probablyResponse == null)
            throw new IllegalArgumentException("Variable has not defined during test execution");
        if (probablyResponse instanceof HttpResponseWrapper response) {
            new HttpResponseVerifier(HttpResponseWrapper.of(checks, context))
                    .test(response);
        } else {
            throw new IllegalArgumentException("Variable has no HTTP response");
        }
    }
}
