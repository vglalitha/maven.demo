package com.test;

import com.dep.Services.TwitterImplement;
import com.dep.models.TweetResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.*;

import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TwitterImplementTest {
    TwitterImplement twitterImplement;
    TwitterFactory twitterFactory;
    Twitter twitter;
    Status status;
    TweetResponse tweetResponse;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    String twitterHandle = "@lalitha_vg";
    String name = "lalitha";
    String message = "hii";
    String profileImageUrl="www.testcase.com";
    Date created;
    String date;
    {
        try {
            created = dateFormat.parse("16-11-2021 01:03:00");
        } catch (ParseException E) {
            E.printStackTrace();
        }
        date = dateFormat.format(created);
    }

    @Before
    public void setUp() {
        status = mock(Status.class);
        twitterFactory = mock(TwitterFactory.class);
        twitter = mock(Twitter.class);
        when(twitterFactory.getInstance()).thenReturn(twitter);
        twitterImplement = new TwitterImplement(twitterFactory, tweetResponse);
        tweetResponse = spy(new TweetResponse(message,twitterHandle,name));

    }

    @Test
    public void checkPost_successCase() {
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
    public void sendTweet_successCase() throws TwitterException {
        Status expectedTweet = mock((Status.class));
        String message = "hii";
        when(twitter.updateStatus(message)).thenReturn(expectedTweet);
        Status actualTweet = twitterImplement.sendTweets(message);
        Assert.assertEquals(expectedTweet, actualTweet);
    }

    @Test
    public void fetchTweet_successCase() throws TwitterException {
        ArrayList<TweetResponse> expectedList = spy(ArrayList.class);
        List<Status> responseList = mock(ResponseList.class);
        User user = mock(User.class);
        Status s1 = mock(Status.class);
        when(responseList.size()).thenReturn(1);
        when(responseList.get(0)).thenReturn(s1);
        when(s1.getUser()).thenReturn(user);
        when(s1.getUser().getProfileImageURL()).thenReturn(profileImageUrl);
        when(s1.getUser().getName()).thenReturn(name);
        when(s1.getUser().getScreenName()).thenReturn(twitterHandle);
        when(s1.getText()).thenReturn(message);
        when(s1.getCreatedAt()).thenReturn(created);
        when(twitter.getHomeTimeline()).thenReturn((ResponseList<Status>) responseList);
        expectedList.add(tweetResponse);
        List<TweetResponse> actuallist = twitterImplement.getFilteredTweets("hii");
        Assert.assertEquals(expectedList.size(), actuallist.size());
    }

    @Test
    public void testCase_postTweet() {
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
    public void fetchNoTweetOnTimeline_successCase() throws TwitterException {
        ResponseList<Status> responseList = mock(ResponseList.class);
        when(responseList.size()).thenReturn(0);
        when(twitter.getHomeTimeline()).thenReturn(responseList);
        Response responseExpected = Response.ok(responseList).build();
        Response responseActual = Response.ok(twitterImplement.fetchLatestTweets()).build();
        Assert.assertEquals(responseExpected.getLength(), responseActual.getLength());

    }


    @Test
    public void noTweetMatch_successCase() throws TwitterException {
        ResponseList<Status> responseList = mock(ResponseList.class);
        when(responseList.size()).thenReturn(0);
        when(twitter.getHomeTimeline()).thenReturn(responseList);
        List<TweetResponse> actual = twitterImplement.getFilteredTweets("forest");
        Assert.assertEquals(Arrays.asList(), actual);
    }


}
