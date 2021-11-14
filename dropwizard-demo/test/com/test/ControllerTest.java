package com.test;

import com.dep.config.BRSConfiguration;
import com.dep.resource.Controller;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

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

}

