package com.guythehunter.messanger.communication.common;

import org.json.JSONObject;
import java.util.Map;

public interface IRequest {
    String getUrl();
    Map<String, String> getHeaders();
    JSONObject getBody();
}
