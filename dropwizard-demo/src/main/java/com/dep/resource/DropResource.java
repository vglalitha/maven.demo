package com.dep.resource;

import com.dep.tweeet;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

@Path("/api/1.0/twitter")
public class DropResource {

    @GET
    @Path("/healthCheck")
    public String healthCheck(){

        return "Ping Received at " + new Date();
    }

    @POST
    @Path("/tweetAgain")
    public String tweetAgain() throws TwitterException {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("vqXgKyjjlw7Isi6yNJQpzaIox")
                .setOAuthConsumerSecret("JCfTCZdPmX1mWkRWFyA4Kxyw7Nao34ctjS1tcjBBNBU4d5nImW")
                .setOAuthAccessToken("1450743367696994308-WM1cr2g13cLdLWQEnXo4QlKPc4Foi6")
                .setOAuthAccessTokenSecret("Pse1CapF8eN5isvZhevLwosdnHFJrU6JZlSolOiilnB2k");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        twitter.updateStatus("again");

        return "Successfully posted";
    }

    @GET
    @Path("/tweet")
    public String time () throws TwitterException {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("vqXgKyjjlw7Isi6yNJQpzaIox")
                .setOAuthConsumerSecret("JCfTCZdPmX1mWkRWFyA4Kxyw7Nao34ctjS1tcjBBNBU4d5nImW")
                .setOAuthAccessToken("1450743367696994308-WM1cr2g13cLdLWQEnXo4QlKPc4Foi6")
                .setOAuthAccessTokenSecret("Pse1CapF8eN5isvZhevLwosdnHFJrU6JZlSolOiilnB2k");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        List<Status> status = twitter.getHomeTimeline();
        for (Status st : status) {
            System.out.println(st.getUser().getName() + "-------" + st.getText());
        }
        return "recieved timeline";
    }
}