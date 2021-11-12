package com.dep.Services;

import com.dep.config.BRSConfiguration;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class TwitterImplement {
   BRSConfiguration brsConfiguration;
   ConfigurationBuilder configurationBuilder;
   TwitterFactory twitterFactory;
   public TwitterImplement(BRSConfiguration brsConfiguration,ConfigurationBuilder configurationBuilder,TwitterFactory twitterFactory)
   {
       this.brsConfiguration=brsConfiguration;
       this.configurationBuilder=configurationBuilder;
       this.twitterFactory=twitterFactory;
   }
   public TwitterImplement(){

   }
   public Twitter getTwitterObject(){
       BRSConfiguration brsConfiguration = new BRSConfiguration();
       ConfigurationBuilder configurationBuilder= brsConfiguration.configurationBuilder();
       TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
       Twitter twitter = twitterFactory.getInstance();
       return twitter;
   }
}

