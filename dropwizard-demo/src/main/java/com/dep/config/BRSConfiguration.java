package com.dep.config;

import io.dropwizard.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class BRSConfiguration extends Configuration {
    public ConfigurationBuilder configurationBuilder(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("6zkV1Og5D6ICoE2fG0scJASDQ")
                .setOAuthConsumerSecret("iAI63TBTZNEBkWJb8tM3qDt0u6MFSz8taoQ9YThIjh7RQEOvme")
                .setOAuthAccessToken("1450743367696994308-sk1MHF6PQ8Puw0l9MhYc0ktjhN8rya")
                .setOAuthAccessTokenSecret("evnGL6EYMuAeBt5ZJDk8UhlFscGQckPjwkE1JwH8QrGgx");
    return configurationBuilder();
    }


}
