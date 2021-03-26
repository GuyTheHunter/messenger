package com.guythehunter.messanger.communication.common;

public class ConnectionToServerFailed extends Exception {
    public ConnectionToServerFailed() {
        super();
    }

    public ConnectionToServerFailed(String message) {
        super(message);
    }
}
