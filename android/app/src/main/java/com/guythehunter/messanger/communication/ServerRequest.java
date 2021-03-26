package com.guythehunter.messanger.communication;

import com.guythehunter.messanger.communication.common.IRequest;

import org.json.JSONObject;

import java.util.Map;

public class ServerRequest implements IRequest {
    private final String url;
    private final Map<String, String> headers;
    private final JSONObject body;

    public ServerRequest(String url, Map<String, String> headers, JSONObject body) {
        this.url = url;
        this.headers = headers;
        this.body = body;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public JSONObject getBody() {
        return this.body;
    }
}
