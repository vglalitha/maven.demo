package com.dep.models;

public class SendResponse {
    private String messages;

    public SendResponse(String message) {
        this.messages = message;
    }

    public SendResponse() {
    }

    public String getMessage() {
        return messages;
    }

    public String setMessage(String message) {
        this.messages = message;
        return message;
    }
}
