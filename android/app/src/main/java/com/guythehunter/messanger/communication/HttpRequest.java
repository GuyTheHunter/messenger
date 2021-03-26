package com.guythehunter.messanger.communication;

import com.guythehunter.messanger.communication.common.IHttpRequest;
import com.guythehunter.messanger.communication.common.IRequest;

import org.json.JSONObject;

import java.util.Map;

public class HttpRequest implements IHttpRequest {
    private final String method, url;
    private final Map<String, String> header;
    private final JSONObject body;

    public HttpRequest(String method, String url, Map<String, String> header, JSONObject body) {
        this.url = url;
        this.body = body;
        this.method = method;
        this.header = header;
    }

    public HttpRequest(String method, IRequest request) {
        this(method, request.getUrl(), request.getHeaders(), request.getBody());
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public Map<String, String> getHeaders() {
        return this.header;
    }

    @Override
    public JSONObject getBody() {
        return this.body;
    }
}
