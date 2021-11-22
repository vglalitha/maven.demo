package com.dep.Services;

import com.dep.config.BRSConfiguration;
import com.dep.models.TweetRespons;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.ws.rs.InternalServerErrorException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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

    public TwitterImplement(TwitterFactory twitterFactory,TweetRespons tweetRespons) {
        this.twitterFactory = twitterFactory;
        this.twitter = twitterFactory.getInstance();
        this.tweetRespons = tweetRespons;
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
                Date createdAt = null;
                Status status = statuses.get(i);
                String message = status.getText();
                String name = status.getUser().getName();
                String profileImageUrl = status.getUser().getProfileImageURL();
                createdAt = status.getCreatedAt();
                Format format = new SimpleDateFormat("dd-mm-yyy HH:mm:ss");
                String date = format.format(createdAt);
                String twitterHandle = status.getUser().getScreenName();
                tweetRespons = new TweetRespons(message, name, twitterHandle, profileImageUrl, date);
                arrayList.add(tweetRespons);
            }
        } catch (TwitterException e) {
        }
        return arrayList;
    }


    public List<TweetRespons> getFilteredTweets(String tweets) {
        ArrayList<TweetRespons> arrayList = new ArrayList<>();
        List<TweetRespons> filteredTweets;
        List<Status> statuses = null;
        try {
            statuses = twitter.getHomeTimeline();
            for (int i = 0; i < statuses.size(); i++) {
                Date createdAt ;
                Status status = statuses.get(i);
                String profileImageUrl = status.getUser().getProfileImageURL();
                String name = status.getUser().getName();
                String message = status.getText();
                createdAt =status.getCreatedAt();
                Format format = new SimpleDateFormat("dd-mm-yyy HH:mm:ss");
                String date = format.format(createdAt);
                String twitterHandle = status.getUser().getScreenName();
                tweetRespons = new TweetRespons(message,name,twitterHandle,profileImageUrl,date);
                arrayList.add(tweetRespons);
            }
        } catch (TwitterException e) {
        }
        int len=tweets.length();
        CharSequence charSequence=tweets.subSequence(0,len);
        filteredTweets=arrayList.stream().filter(t->t.getMessage().contains(charSequence)).collect(Collectors.toList());
        return filteredTweets;
    }
}


