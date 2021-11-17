package com.dep.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.List;

public class GetTimelineTweets {
    TwitterImplement twitterImplement;
    Logger logger = LoggerFactory.getLogger(GetTimelineTweets.class);

    public GetTimelineTweets(TwitterImplement twitterImplement) {
        this. twitterImplement= twitterImplement;
    }
    public GetTimelineTweets(){
    }


}
