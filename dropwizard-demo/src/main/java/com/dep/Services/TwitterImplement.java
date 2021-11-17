package com.dep.Services;

import com.dep.config.BRSConfiguration;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.ws.rs.InternalServerErrorException;
import java.util.ArrayList;
import java.util.List;


public class TwitterImplement {
   BRSConfiguration brsConfiguration;
   ConfigurationBuilder configurationBuilder;
   TwitterFactory twitterFactory;
   Twitter twitter;

   public TwitterImplement(){
       brsConfiguration = new BRSConfiguration();
       configurationBuilder = brsConfiguration.configurationBuilder();
       twitterFactory = new TwitterFactory(configurationBuilder.build());
       twitter = twitterFactory.getInstance();

   }

   public TwitterImplement(TwitterFactory twitterFactory)
   {
       this.twitterFactory=twitterFactory;
       this.twitter = twitterFactory.getInstance();
   }

    public Status sendTweets(String tweet) throws TwitterException {
        Status status;
        status = twitter.updateStatus(tweet);

        return status;
    }

    public ArrayList<String> fetchLatestTweets()
    {
        ArrayList<String> arrayList = new ArrayList<>();
        try{
            List<Status> statuses = twitter.getHomeTimeline();
            for (int i=0;i<statuses.size();i++){
                Status status = statuses.get(i);
                arrayList.add(status.getText());
            }
        }catch (TwitterException e)
        {
            throw new InternalServerErrorException("server error");
        }
        return arrayList;
    }
}

