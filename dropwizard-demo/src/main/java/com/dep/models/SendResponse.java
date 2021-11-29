package com.dep.models;

public class SendResponse {
    private String message;
    private int statusCode;

    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public SendResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
    public SendResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
