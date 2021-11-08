package com.dep.config;

import io.dropwizard.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class BRSConfiguration extends Configuration {
    public ConfigurationBuilder configurationBuilder(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("0KlyQjH8JKKmaLjRKtnYdtKmy")
                .setOAuthConsumerSecret("6ANXkVb8xqzahPv2HZ16ekakkxelbOO53a3dYBJRzPnts9Rklo")
                .setOAuthAccessToken("1450743367696994308-9OWFa1GAdHbkGJ54UVYaPrQAmkz4w7")
                .setOAuthAccessTokenSecret("swUdq6b4qbBiOpN6F9Lo9pd8tvVfcV0UknsZHN842z6tM");
    return configurationBuilder();
    }


}
