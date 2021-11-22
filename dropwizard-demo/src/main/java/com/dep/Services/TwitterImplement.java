package com.dep.Services;

import com.dep.config.BRSConfiguration;
import com.dep.models.TweetResponse;
import org.slf4j.LoggerFactory;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class TwitterImplement {
    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(TwitterImplement.class);
    BRSConfiguration brsConfiguration;
    TwitterFactory twitterFactory;
    ConfigurationBuilder configurationBuilder;
    Twitter twitter;
    TweetResponse tweetResponse;

    public TwitterImplement() {
        brsConfiguration = new BRSConfiguration();
        configurationBuilder = brsConfiguration.configurationBuilder();
        twitterFactory = new TwitterFactory(configurationBuilder.build());
        twitter = twitterFactory.getInstance();


    }

    public TwitterImplement(TwitterFactory twitterFactory, TweetResponse tweetResponse) {
        this.twitterFactory = twitterFactory;
        this.twitter = twitterFactory.getInstance();
        this.tweetResponse = tweetResponse;
    }

    public Status sendTweets(String tweet) throws TwitterException {
        Status status;
        status = twitter.updateStatus(tweet);
        return status;
    }


    public ArrayList<TweetResponse> fetchLatestTweets() {
        ArrayList<TweetResponse> arrayList = new ArrayList<>();
        List<Status> statuses = null;
        try {
            statuses = twitter.getHomeTimeline();
            for (int i = 0; i < statuses.size(); i++) {
                Date createdAt = null;
                Status status = statuses.get(i);
                String message = status.getText();
                String name = status.getUser().getName();
                String profileImageUrl = status.getUser().getProfileImageURL();
                createdAt = status.getCreatedAt();
                Format format = new SimpleDateFormat("dd-mm-yyy HH:mm:ss");
                String date = format.format(createdAt);
                String twitterHandle = status.getUser().getScreenName();
                tweetResponse = new TweetResponse(message, name, twitterHandle, profileImageUrl, date);
                arrayList.add(tweetResponse);
            }
        } catch (TwitterException e) {
            logger.error("error in fetching tweets");
        }
        return arrayList;
    }

    public List<TweetResponse> getFilteredTweets(String tweets) {
        ArrayList<TweetResponse> listTweets = new ArrayList<>();
        List<TweetResponse> filteredTweets;
        List<Status> statuses = null;
        listTweets = fetchLatestTweets();
        int len=tweets.length();
        CharSequence charSequence=tweets.subSequence(0,len);
        filteredTweets=listTweets.stream().filter(t->t.getMessage().contains(charSequence)).collect(Collectors.toList());
        return filteredTweets;
    }

}


