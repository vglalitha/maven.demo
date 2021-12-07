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
    /**
     * Used to send response on tweet posted.
     *
     * @param message is a response message.
     */

    public String setMessage(String message) {
        this.messages = message;
        return message;
    }
}
