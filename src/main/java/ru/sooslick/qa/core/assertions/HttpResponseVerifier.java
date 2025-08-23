package ru.sooslick.qa.core.assertions;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import ru.sooslick.qa.core.http.HttpResponseWrapper;

import java.util.Map;

/**
 * Assertions for HTTP response
 */
@AllArgsConstructor
public class HttpResponseVerifier implements Verifier {

    private final HttpResponseWrapper expectedResponse;

    /**
     * Performs a check that given response matches expected template
     *
     * @param actualResponse response to check
     */
    public void test(HttpResponseWrapper actualResponse) {
        Assertions.assertAll(
                () -> {
                    if (expectedResponse.getCode() != null)
                        testCode(actualResponse.getCode());
                },
                () -> {
                    if (expectedResponse.getHeaders() != null)
                        testHeaders(actualResponse.getHeaders());
                },
                () -> {
                    if (expectedResponse.getBody() != null)
                        testBody(actualResponse.getBody());
                }
        );
    }

    /**
     * Performs a check that given code matches the expected one
     *
     * @param actualCode code to check
     */
    public void testCode(int actualCode) {
        Assertions.assertEquals(expectedResponse.getCode(), actualCode);
    }

    /**
     * Performs a check that given headers contains all headers from expected template
     *
     * @param actualHeaders Map "Header - Value" to check
     */
    public void testHeaders(Map<String, String> actualHeaders) {
        new CollectionVerifier<>(expectedResponse.getHeaders().entrySet())
                .testContains(actualHeaders.entrySet());
    }

    /**
     * Performs a check that given body matches the expected one
     *
     * @param actualBody content to check
     */
    public void testBody(String actualBody) {
        new StringVerifier(expectedResponse.getBody()).test(actualBody);
    }
}
