package com.dep.resource;

import com.dep.Services.GetTimelineTweets;
import com.dep.Services.PostTweet;
import com.dep.Services.TwitterImplement;


public class Request {
    String message;
    TwitterImplement twitterImplement;
    public Request(TwitterImplement twitterImplement) {
        this.twitterImplement=twitterImplement;
    }
    public Request()
    {}
    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }

}