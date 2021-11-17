package com.test;

import com.dep.Services.TwitterImplement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.*;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TwitterImplementTest {
    TwitterImplement twitterImplement;
    TwitterFactory twitterFactory;
    Twitter twitter;
    Status status;

    @Before
    public void setUp() {
        status = mock(Status.class);
        twitterFactory = mock(TwitterFactory.class);
        twitter = mock(Twitter.class);
        when(twitterFactory.getInstance()).thenReturn(twitter);
        twitterImplement = new TwitterImplement(twitterFactory);

    }

    @Test
    public void testcase2_checkpost() throws TwitterException {
        String msg = "message";
        when(twitter.updateStatus(msg)).thenReturn(status);
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
        ResponseList<Status> responseList = mock(ResponseList.class);
        Status s1 = mock(Status.class);
        Status s2 = mock(Status.class);
        Status s3 = mock(Status.class);
        when(responseList.size()).thenReturn(3);
        when(responseList.get(0)).thenReturn(s1);
        when(s1.getText()).thenReturn("Tweet1");
        when(responseList.get(1)).thenReturn(s2);
        when(s2.getText()).thenReturn("Tweet2");
        when(responseList.get(2)).thenReturn(s3);
        when(s3.getText()).thenReturn("Tweet3");
        when(twitter.getHomeTimeline()).thenReturn(responseList);

        List<String> expected = Arrays.asList("Tweet1", "Tweet2", "Tweet3");

        List<String> actual = twitterImplement.fetchLatestTweets();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testcase1_length() throws TwitterException {
        String msg = "check postlength";
        when(twitter.updateStatus(msg)).thenReturn(status);
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


}
