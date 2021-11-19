package com.dep.Services;

import com.dep.config.BRSConfiguration;
import com.dep.models.TweetRespons;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TwitterImplement {
    BRSConfiguration brsConfiguration;
    TwitterFactory twitterFactory;
    ConfigurationBuilder configurationBuilder;
    Twitter twitter;
    TweetRespons tweetRespons;

    public TwitterImplement() {
        brsConfiguration = new BRSConfiguration();
        configurationBuilder = brsConfiguration.configurationBuilder();
        twitterFactory = new TwitterFactory(configurationBuilder.build());
        twitter = twitterFactory.getInstance();


    }

    public TwitterImplement(TwitterFactory twitterFactory) {
        this.twitterFactory = twitterFactory;
        this.twitter = twitterFactory.getInstance();
    }

    public Status sendTweets(String tweet) throws TwitterException {
        Status status;
        status = twitter.updateStatus(tweet);
        return status;
    }


    public ArrayList<TweetRespons> fetchLatestTweets() {

        ArrayList<TweetRespons> arrayList = new ArrayList<>();
        List<Status> statuses = null;
        try {
            statuses = twitter.getHomeTimeline();
            for (int i = 0; i < statuses.size(); i++) {
                String twitterHandle;
                String name;
                String message = null;
                String profileImageUrl = null;
                Date createdAt = null;
                Status status = statuses.get(i);
                message = status.getText();
                name = status.getUser().getName();
                profileImageUrl = status.getUser().getProfileImageURL();
                createdAt = status.getCreatedAt();
                Format format = new SimpleDateFormat("dd-mm-yyy HH:mm:ss");
                String date = format.format(createdAt);
                twitterHandle = status.getUser().getScreenName();
                tweetRespons = new TweetRespons(message, name, twitterHandle, profileImageUrl, date);
                arrayList.add(tweetRespons);
            }
        } catch (TwitterException e) {
        }
        return arrayList;
    }
}

