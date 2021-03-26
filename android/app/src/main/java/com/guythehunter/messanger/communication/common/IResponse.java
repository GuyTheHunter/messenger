package com.guythehunter.messanger.communication.common;

import org.json.JSONObject;

import java.util.Map;

public interface IResponse {
    int getStatusCode();
    JSONObject getBody();
    Map<String, String> getHeaders();
}
