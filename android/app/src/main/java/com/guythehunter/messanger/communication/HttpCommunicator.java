package com.guythehunter.messanger.communication;

import android.os.AsyncTask;

import com.guythehunter.messanger.communication.ServerResponse;
import com.guythehunter.messanger.communication.common.IHttpRequest;
import com.guythehunter.messanger.communication.common.IResponse;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpCommunicator extends AsyncTask<IHttpRequest, Void, IResponse> {

    @Override
    protected IResponse doInBackground(IHttpRequest... requests) {
        if (0 == requests.length)
            return null;

        final HttpURLConnection connection;
        try {
            connection = this.buildHttpRequest(requests[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        try {
            final int statusCode = connection.getResponseCode();
            final String serverResponse = this.getServerResponse(connection);
            final Map<String, String> headers = this.getResponseHeaders(connection);

            if (!this.isResponseOk(connection))
                throw new Exception(String.format("Invalid Server Response, status code: %s, reason: %s", statusCode, serverResponse));

            return new ServerResponse(statusCode, new JSONObject(serverResponse), headers);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            connection.disconnect();
        }
    }

    private HttpURLConnection buildHttpRequest(IHttpRequest request) throws IOException {
        URL _url = new URL(request.getUrl());
        HttpURLConnection connection = (HttpURLConnection) _url.openConnection();

        Map<String, String> _headers;
        if (null == request.getHeaders()) {
            _headers = new HashMap<>();
        } else {
            _headers = request.getHeaders();
        }

        String httpBody = null;
        if (null != request.getBody()) {
            _headers.put("Content-Type", "application/json");
            httpBody = request.getBody().toString();
        }

        for (Map.Entry<String, String> entry : _headers.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }

        if (null != httpBody) {
            connection.setDoOutput(true);

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(httpBody);
            outputStream.flush();
            outputStream.close();
        }

        return connection;
    }

    private String getServerResponse(HttpURLConnection connection) throws IOException {
        String inputBuffer;
        StringBuilder serverResponse = new StringBuilder();
        InputStream stream = this.isResponseOk(connection) ? connection.getInputStream() : connection.getErrorStream();
        BufferedReader inputStream = new BufferedReader(new InputStreamReader(stream));

        while (null != (inputBuffer = inputStream.readLine()))
            serverResponse.append(inputBuffer);

        stream.close();
        inputStream.close();
        return serverResponse.toString();
    }

    private Map<String, String> getResponseHeaders(HttpURLConnection connection) {
        Map<String, String> headers = new HashMap<>();
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, List<String>> entry : connection.getHeaderFields().entrySet()) {
            builder.setLength(0);
            for (String value : entry.getValue()) {
                builder.append(String.format("%s, ", value));
            }
            headers.put(entry.getKey(), builder.toString());
        }

        return headers;
    }

    private boolean isResponseOk(HttpURLConnection connection) throws IOException {
        return 200 == connection.getResponseCode();
    }
}
