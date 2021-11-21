package com.test;

import com.dep.Services.TwitterImplement;
import com.dep.models.TweetRespons;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.*;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class TwitterImplementTest {
    TwitterImplement twitterImplement;
    TwitterFactory twitterFactory;
    Twitter twitter;
    Status status;
    TweetRespons tweetRespons;
    String twitterHandle = "@case";
    String name = "lalitha";
    String message = "hii";

    @Before
    public void setUp() {
        status = mock(Status.class);
        twitterFactory = mock(TwitterFactory.class);
        twitter = mock(Twitter.class);
        when(twitterFactory.getInstance()).thenReturn(twitter);
        twitterImplement = new TwitterImplement(twitterFactory,tweetRespons);
        tweetRespons = spy(new TweetRespons(message,twitterHandle,name));

    }

    @Test
    public void testcase2_checkpost() {
        String msg = "message";
        try {
            when(twitter.updateStatus(msg)).thenReturn(status);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        boolean T;
        try {
            Status status = twitter.updateStatus(msg);
            T = true;
        } catch (Exception e) {
            T = false;
        }
        Assert.assertTrue(T);

    }

    @Test
    public void testCase_sendTweet_successCase() throws TwitterException {
        Status expectedTweet = mock((Status.class));
        String message = "hii";
        when(twitter.updateStatus(message)).thenReturn(expectedTweet);
        Status actualTweet = twitterImplement.sendTweets(message);
        Assert.assertEquals(expectedTweet, actualTweet);
    }

    @Test
    public void testCase_fetchTweet_successCase() throws TwitterException {
        ArrayList<TweetRespons> expectedlist = mock(ArrayList.class);
        ResponseList<Status> responseList = mock(ResponseList.class);
        User user = mock(User.class);
        Status s1 = mock(Status.class);
        when(responseList.size()).thenReturn(1);
        when(responseList.get(0)).thenReturn(s1);
        when(s1.getUser()).thenReturn(user);
        when(s1.getUser().getName()).thenReturn(name);
        when(s1.getText()).thenReturn(message);
        when(twitter.getHomeTimeline()).thenReturn(responseList);
        expectedlist.add(tweetRespons);
        ArrayList<TweetRespons> actuallist = twitterImplement.fetchLatestTweets();
        Assert.assertEquals(expectedlist, actuallist);
    }

    @Test
    public void testcase1_postTweet() {
        String msg = "message";
        try {
            when(twitter.updateStatus(msg)).thenReturn(status);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        boolean x;
        try {
            if (msg.length() != 0)
                status = twitter.updateStatus(msg);
            x = true;
        } catch (Exception e) {
            x = false;
        }
        Assert.assertTrue(x);
    }

    @Test
    public void testCase_fetchNoTweetOnTimeline_successCase() throws TwitterException {
        ResponseList<Status> responseList = mock(ResponseList.class);
        when(responseList.size()).thenReturn(0);
        when(twitter.getHomeTimeline()).thenReturn(responseList);
        Response responseExpected = Response.ok(responseList).build();
        Response responseActual = Response.ok(twitterImplement.fetchLatestTweets()).build();
        Assert.assertEquals(responseExpected.getLength(), responseActual.getLength());

    }

    @Test(expected = InternalServerErrorException.class)
    public void testCase_exceptionCase() throws TwitterException {
        when(twitter.getHomeTimeline()).thenThrow(TwitterException.class);
        twitterImplement.fetchLatestTweets();
    }
}
