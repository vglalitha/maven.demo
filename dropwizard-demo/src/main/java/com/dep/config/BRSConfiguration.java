package com.dep.config;

import io.dropwizard.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class BRSConfiguration extends Configuration {
    String filepath="twitter4j.yml";
    String accessTokenSecret="";
    String accessToken="";
    String consumerSecret="";
    String consumerKey="";
    Properties properties= new Properties();
    FileInputStream fileInputStream;
    {
        try{
            fileInputStream = new FileInputStream(filepath);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        try{
            properties.load(fileInputStream);
        }catch (IOException e) {
            e.printStackTrace();
        }
        accessTokenSecret =properties.getProperty("accessTokenSecret");
        accessToken = properties.getProperty("accessToken");
        consumerSecret = properties.getProperty("consumerSecret");
        consumerKey = properties.getProperty("consumerKey");

    }
   public ConfigurationBuilder configurationBuilder(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("consumerKey")
                .setOAuthConsumerSecret("consumerSecret")
                .setOAuthAccessToken("accessToken")
                .setOAuthAccessTokenSecret("accessTokenSecret");
    return configurationBuilder();
    }



}
