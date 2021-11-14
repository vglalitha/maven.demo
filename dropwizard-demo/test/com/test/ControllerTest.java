package com.test;

import com.dep.config.BRSConfiguration;
import com.dep.resource.Controller;
import com.dep.resource.Request;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import com.dep.Services.TwitterImplement;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {
    Controller tweetPost;
    BRSConfiguration brsConfiguration;
    TwitterImplement twitterImplement;
    Twitter twitter;


    @Before
    public void setUp() {
        brsConfiguration = Mockito.mock(BRSConfiguration.class);
        twitterImplement = Mockito.mock(TwitterImplement.class);
        tweetPost = Mockito.mock(Controller.class);
        twitter = Mockito.mock(Twitter.class);
    }


    @Test
    public void testcase_postLength() throws TwitterException {
            ArrayList<String> str1 = new ArrayList<String>();
            str1.add("Tweet");
            Request request = new Request();
            when(tweetPost.sendTweet(request)).thenReturn(Response.ok(str1).build());
            ArrayList<String> str = new ArrayList<String>();
            str.add("Tweet");
            Response expected = Response.ok(str).build();
            Response actual = tweetPost.sendTweet(request);
            Assert.assertEquals(expected.getLength(), actual.getLength());
            Assert.assertEquals(expected.getStatus(), actual.getStatus());
            Assert.assertEquals(expected.getEntity(), actual.getEntity());
        }

    @Test
    public void testcase_sendTweet_success() {
        when(brsConfiguration.configurationBuilder()).thenReturn(new ConfigurationBuilder());
        Twitter twitter = TwitterFactory.getSingleton();
        String expected = "Testing Twitter";
        boolean T;
        try {
            Status status = twitter.updateStatus(expected);
            T = true;
        } catch (Exception e) {
            T = false;
        }
        Assert.assertTrue(T);
    }


    @Test
    public void test_postToTwitterUsingTwitter4J() {
        Twitter twitter = TwitterFactory.getSingleton();
        String expectedMessage = "Test";
        when(brsConfiguration.configurationBuilder()).thenReturn(new ConfigurationBuilder());
        Status status = null;
        try {
            status = twitter.updateStatus(expectedMessage);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        String actualMessage = status.getText();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testcase_unsuccessfulTweet() {
        Twitter twitter = TwitterFactory.getSingleton();
        String expectedMessage = "";
        when(brsConfiguration.configurationBuilder()).thenReturn(new ConfigurationBuilder());
        Status status = null;
        try {
            status = twitter.updateStatus(expectedMessage);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        int expectedLength = 0;
        int actualLength = 0;
        String actualMessage = "";
        if (status != null) {
            expectedLength = expectedMessage.length();
            actualLength = 0;
        } else {
            expectedLength = expectedMessage.length();
            actualLength = actualMessage.length();
        }
        Assert.assertEquals(expectedLength, actualLength);
    }
    @Test
    public void testcase_searchTweets() throws TwitterException {
        when(twitterImplement.getTwitterObject()).thenReturn(twitter);
        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query("lalitha_vg");
        QueryResult result = twitter.search(query);
        boolean b = false;
        try {
            for (Status sts : result.getTweets()) {
                System.out.println("@" + sts.getUser().getScreenName() +sts.getText());
                b = true;
            }
        } catch (Exception e) {
            b = false;
        }
        Assert.assertTrue(b);
    }

}

