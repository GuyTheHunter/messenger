package com.guythehunter.messanger.communication;

import com.guythehunter.messanger.communication.common.ConnectionToServerFailed;

import com.guythehunter.messanger.communication.common.IHttpRequest;
import com.guythehunter.messanger.communication.common.IRequest;
import com.guythehunter.messanger.communication.common.IResponse;
import com.guythehunter.messanger.communication.common.ICommunication;

public class ServerCommunicator implements ICommunication {
    private final HttpCommunicator httpCommunicator;

    public ServerCommunicator() {
        this.httpCommunicator = new HttpCommunicator();
    }

    private IResponse sendHttpRequest(String method, IRequest request) throws ConnectionToServerFailed {
        IHttpRequest httpRequest = new HttpRequest(method, request);
        this.httpCommunicator.execute(httpRequest);

        try {
            IResponse response = this.httpCommunicator.get();

            if (null == response)
                throw new Exception("Failed to get response from server");
            return response;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectionToServerFailed(e.getMessage());
        }
    }

    @Override
    public IResponse get(IRequest request) throws ConnectionToServerFailed {
        return this.sendHttpRequest("get", request);
    }

    @Override
    public IResponse post(IRequest request) throws ConnectionToServerFailed {
        return this.sendHttpRequest("post", request);
    }
}