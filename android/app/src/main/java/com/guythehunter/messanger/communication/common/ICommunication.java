package com.guythehunter.messanger.communication.common;

public interface ICommunication {
    IResponse get(IRequest request) throws ConnectionToServerFailed;
    IResponse post(IRequest request) throws ConnectionToServerFailed;
}
