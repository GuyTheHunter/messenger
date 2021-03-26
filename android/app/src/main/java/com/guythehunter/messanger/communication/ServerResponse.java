package com.guythehunter.messanger.communication;

import com.guythehunter.messanger.communication.common.IResponse;

import org.json.JSONObject;

import java.util.Map;

public class ServerResponse implements IResponse {
    private final int statusCode;
    private final JSONObject jsonBody;
    private final Map<String, String> headers;

    public ServerResponse(int statusCode, JSONObject body, Map<String, String> headers) {
        this.jsonBody = body;
        this.headers = headers;
        this.statusCode = statusCode;
    }

    @Override
    public int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public JSONObject getBody() {
        return this.jsonBody;
    }

    @Override
    public Map<String, String> getHeaders() {
        return this.headers;
    }
}
