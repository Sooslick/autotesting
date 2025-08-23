package ru.sooslick.qa.core.http;

import io.cucumber.datatable.DataTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.helper.DataGeneratorsHelper;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Wrapper class for HTTP request for HttpRequestSteps
 */
@NoArgsConstructor
@Getter
public class HttpRequestWrapper {
    @Setter
    private String method;
    private List<String> queryParams;
    private HashMap<String, String> headers;
    private String body;

    /**
     * Creates Request template from Cucumber DataTable
     *
     * @param properties response attributes
     * @param context    current scenario context
     * @return request template
     */
    public static HttpRequestWrapper of(DataTable properties, ScenarioContext context) {
        HttpRequestWrapper rp = new HttpRequestWrapper();
        for (var kv : properties.asLists()) {
            String k = kv.get(0).toLowerCase();
            String v = DataGeneratorsHelper.processString(kv.get(1), context);
            switch (k) {
                case "method" -> rp.setMethod(v);
                case "query parameter" -> rp.addQueryParam(v);
                case "header" -> rp.addHeader(v);
                case "body" -> rp.setBody(v);
            }
        }
        return rp;
    }

    public void addQueryParam(String param) {
        if (queryParams == null)
            queryParams = new LinkedList<>();
        queryParams.add(param);
    }

    public void addHeader(String header) {
        if (headers == null)
            headers = new HashMap<>();
        String[] kv = header.split("=", 2);
        headers.put(kv[0], kv[1]);
    }

    public void setBody(String content) {
        body = content;
    }

    /**
     * Create OkHttp3 Request to given URL from this template
     *
     * @param url request address
     * @return OkHttp3 Request
     */
    public Request buildRequest(String url) {
        Request.Builder req = new Request.Builder();
        req.url(buildUrl(url));
        if (headers != null)
            req.headers(Headers.of(headers));
        req.method(method, buildBody());
        return req.build();
    }

    private String buildUrl(String url) {
        if (queryParams == null)
            return url;
        else
            return url + "?" + String.join("&", queryParams);
    }

    private RequestBody buildBody() {
        if (body == null)
            return null;
        return RequestBody.create(body.getBytes());
    }
}
