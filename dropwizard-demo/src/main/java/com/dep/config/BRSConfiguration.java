package com.dep.config;

import io.dropwizard.Configuration;
import twitter4j.conf.ConfigurationBuilder;
<<<<<<< HEAD
/*configurations*/
=======

>>>>>>> fd1af61 (conflict issue)
public class BRSConfiguration extends Configuration {
    public ConfigurationBuilder configurationBuilder(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
<<<<<<< HEAD
                .setOAuthConsumerKey("6zkV1Og5D6ICoE2fG0scJASDQ")
                .setOAuthConsumerSecret("iAI63TBTZNEBkWJb8tM3qDt0u6MFSz8taoQ9YThIjh7RQEOvme")
                .setOAuthAccessToken("1450743367696994308-sk1MHF6PQ8Puw0l9MhYc0ktjhN8rya")
                .setOAuthAccessTokenSecret("evnGL6EYMuAeBt5ZJDk8UhlFscGQckPjwkE1JwH8QrGgx");
=======
                .setOAuthConsumerKey("0KlyQjH8JKKmaLjRKtnYdtKmy")
                .setOAuthConsumerSecret("6ANXkVb8xqzahPv2HZ16ekakkxelbOO53a3dYBJRzPnts9Rklo")
                .setOAuthAccessToken("1450743367696994308-9OWFa1GAdHbkGJ54UVYaPrQAmkz4w7")
                .setOAuthAccessTokenSecret("swUdq6b4qbBiOpN6F9Lo9pd8tvVfcV0UknsZHN842z6tM");
>>>>>>> fd1af61 (conflict issue)
    return configurationBuilder();
    }


}
