package com.dep.resource;

/*** this class is used to take tweet from user
 */
public class Request {
    String message;

    public Request() {
    }

    public Request(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}