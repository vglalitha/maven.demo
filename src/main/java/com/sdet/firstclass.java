package com.sdet;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class firstclass {
    public static void main (String[] args) throws TwitterException {
        secondclass object = new secondclass();
        object.time();
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("uCqQl9INwhN22ysdP4gzhri4u")
                .setOAuthConsumerSecret("GNLgpPzWYAhnXGpOzKhV2D5WwCx7nigpOGG9Hccb2JeX3NaiHI")
                .setOAuthAccessToken("1450743367696994308-s3tLRyaDFFMe91bfZ4C1MaToeZPvsI")
                .setOAuthAccessTokenSecret("FfgXirsqHLrPmXOFZqoe8Rk1zpTHLQlIqLnvd8cn3avcg");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        twitter.updateStatus("hiiiii");
    }
}
