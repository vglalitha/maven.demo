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

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.Response.ok;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {
    Controller tweetPost;
    BRSConfiguration brsConfiguration;


    @Before
    public void setUp() {
        brsConfiguration = Mockito.mock(BRSConfiguration.class);
        tweetPost = Mockito.mock(Controller.class);
    }

    @Test
    public void testcase1_getTweets() {
        //when(brsConfiguration.configurationBuilder()).thenReturn(new ConfigurationBuilder());
        ArrayList<String> str = new ArrayList<String>();
        str.add("hlo");
        when(tweetPost.GetTweets()).thenReturn(ok(str).build());
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("hlo");
        Response expectedTweet = ok(arrayList).build();
        Response actualTweet = tweetPost.GetTweets();
        Assert.assertEquals(expectedTweet.getStatus(), actualTweet.getStatus());
        Assert.assertEquals(expectedTweet.getStatus(), actualTweet.getStatus());
    }

    @Test
    public void testcase_noTweetsFound() {
        //when(brsConfiguration.configurationBuilder()).thenReturn(new ConfigurationBuilder());
        when(tweetPost.GetTweets()).thenReturn(Response.ok().build());
        Response expectedTweet = Response.ok().build();
        Response actualTweet = tweetPost.GetTweets();
        Assert.assertEquals(expectedTweet.getEntity(), actualTweet.getEntity());
        Assert.assertEquals(expectedTweet.getStatus(), actualTweet.getStatus());
    }


    @Test
    public void testcase2_getTweets() throws TwitterException {
        //when(brsConfiguration.configurationBuilder()).thenReturn(new ConfigurationBuilder());
        Twitter twitter = TwitterFactory.getSingleton();
        ArrayList<String> arrayList = new ArrayList<String>();
        List<Status> status = twitter.getHomeTimeline();
        for (Status st : status) {
            arrayList.add(st.getText());
        }
        ArrayList<String> expectedTweet = arrayList;
        ArrayList<String> actualTweet = Controller.getTweets();
        Assert.assertEquals(expectedTweet, actualTweet);
    }

    @Test
    public void testcase_postLength() throws TwitterException {
        //when(brsConfiguration.configurationBuilder()).thenReturn(new ConfigurationBuilder());
        ArrayList<String> str1 = new ArrayList<String>();
        str1.add("msg");
        Request request = new Request();
        String post = request.getMessage();
        when(tweetPost.tweetAgain(request)).thenReturn(ok(str1).build());
        ArrayList<String> str = new ArrayList<String>();
        str.add("msg");
        Response expected = ok(str).build();
        Response actual = tweetPost.tweetAgain(request);
        Assert.assertEquals(expected.getLength(), actual.getLength());
        Assert.assertEquals(expected.getEntity(), actual.getEntity());
        Assert.assertEquals(expected.getStatus(), actual.getStatus());
    }

    @Test
    public void testcase_sendTweet_success() {
        //when(brsConfiguration.configurationBuilder()).thenReturn(new ConfigurationBuilder());
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
    public void testcase_searchTweets() throws TwitterException {
        //when(brsConfiguration.configurationBuilder()).thenReturn(new ConfigurationBuilder());
        ArrayList<String> tweet = new ArrayList<String>();
        tweet.add("hlo");
        tweet.add("hi");
        when(tweetPost.GetTweets()).thenReturn(ok(tweet).build());
        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query("source:twitter4j lalitha_vg");
        QueryResult result = twitter.search(query);
        boolean b = false;
        try {
            for (Status status : result.getTweets()) {
                System.out.println(status);
                b = true;
            }
        } catch (Exception e) {
            b = false;
        }
        Assert.assertFalse(b);
    }


    @Test
    public void test_postToTwitterUsingTwitter4J() {
        Twitter twitter = TwitterFactory.getSingleton();
        String expectedMessage = "Test";
        //when(brsConfiguration.configurationBuilder()).thenReturn(new ConfigurationBuilder());
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

}

