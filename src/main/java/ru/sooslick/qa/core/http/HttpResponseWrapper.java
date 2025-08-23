package ru.sooslick.qa.core.http;

import io.cucumber.datatable.DataTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.helper.DataGeneratorsHelper;

import java.util.HashMap;

/**
 * Wrapper class for HTTP response for HttpRequestSteps
 */
@NoArgsConstructor
@Getter
public class HttpResponseWrapper {
    @Setter
    private Integer code;
    private HashMap<String, String> headers;
    @Setter
    private String body;

    /**
     * Creates Response template from Cucumber DataTable
     *
     * @param properties response attributes
     * @param context    current scenario context
     * @return response template
     */
    public static HttpResponseWrapper of(DataTable properties, ScenarioContext context) {
        HttpResponseWrapper rw = new HttpResponseWrapper();
        for (var kv : properties.asLists()) {
            String k = kv.get(0).toLowerCase();
            String v = DataGeneratorsHelper.processString(kv.get(1), context);
            switch (k) {
                case "code" -> rw.setCode(Integer.parseInt(v));
                case "header" -> rw.addHeader(v);
                case "body" -> rw.setBody(v);
            }
        }
        return rw;
    }

    /**
     * Constructor to wrap OkHttp3 Response
     *
     * @param code    HTTP code
     * @param headers OkHttp3 Headers
     * @param body    HTTP response body
     */
    @SneakyThrows
    public HttpResponseWrapper(Integer code, Headers headers, ResponseBody body) {
        this.code = code;
        if (headers != null) {
            this.headers = new HashMap<>();
            headers.iterator().forEachRemaining(p -> this.headers.put(p.getFirst(), p.getSecond()));
        }
        if (body != null)
            this.body = body.string();
    }

    public void addHeader(String header) {
        if (headers == null)
            headers = new HashMap<>();
        String[] kv = header.split("=", 2);
        headers.put(kv[0], kv[1]);
    }
}
