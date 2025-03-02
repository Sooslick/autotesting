package ru.sooslick.qa.core.assertions;

import io.cucumber.datatable.DataTable;
import lombok.SneakyThrows;
import okhttp3.Response;
import org.junit.jupiter.api.Assertions;
import org.opentest4j.MultipleFailuresError;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.helper.DataGeneratorsHelper;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * todo javadoc
 */
public class HttpResponseVerifier {

    private final Response response;
    private final List<Runnable> assertions;

    public HttpResponseVerifier(Response response, DataTable checks, ScenarioContext context) {
        this.response = response;
        this.assertions = checks.asLists().stream()
                .map(l -> createAssertion(l.get(0), DataGeneratorsHelper.processString(l.get(1), context)))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public void test() {
        LinkedList<Throwable> failedChecks = new LinkedList<>();
        for (Runnable r : assertions) {
            try {
                r.run();
            } catch (Throwable e) {
                failedChecks.add(e);
            }
        }
        if (failedChecks.size() == 1)
            throw failedChecks.get(0);
        else if (failedChecks.size() > 1)
            throw new MultipleFailuresError("Multiple Response assertions failed", failedChecks);
    }

    @SneakyThrows
    private Runnable createAssertion(String name, String expectedValue) {
        // todo ugly switch expr, use extensible approach like @data generator
        switch (name) {
            case "code" -> {
                return () -> Assertions.assertEquals(expectedValue, String.valueOf(response.code()), "Unexpected response code");
            }
            case "body" -> {
                return () -> {
                    try {
                        Assertions.assertNotNull(response.body(), "Response has no body, expected object");
                        //noinspection ConstantConditions
                        String body = response.body().string();
                        new StringVerifier(expectedValue).test(body);
                    } catch (IOException e) {
                        Assertions.fail("Unable to read Response body");
                    }
                };
            }
            default -> {
                return () -> {
                    throw new UnsupportedOperationException("Unknown response check: " + name);
                };
            }
        }
    }
}
