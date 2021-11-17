package com.dep.Services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;


public class PostTweet {
    static TwitterImplement twitterImplement;

    public PostTweet(TwitterImplement twitterImplement) {

        this.twitterImplement = twitterImplement;
    }

    public PostTweet() {

    }
}
