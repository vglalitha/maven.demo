package com.dep.resource;

import com.dep.Services.GetTimelineTweets;
import com.dep.Services.PostTweet;
import com.dep.Services.TwitterImplement;
import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.TwitterException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;


@Produces(MediaType.APPLICATION_JSON)
@Path("/api/1.0/twitter")
public class Controller {
    public static final Logger logger = LoggerFactory.getLogger(Controller.class);
    Request request;
    PostTweet postTweet;
    GetTimelineTweets getTimelineTweets;
    TwitterImplement twitterImplement = new TwitterImplement();

    public Controller(Request request, PostTweet postTweet, GetTimelineTweets getTimelineTweets, TwitterImplement twitterImplement) {
        this.request = request;
        this.postTweet = postTweet;
        this.getTimelineTweets = getTimelineTweets;
        this.twitterImplement = twitterImplement;
    }

    public Controller() {
    }

    @GET
    @Path("GetTweets")
    public Response fetchTweets(Request request) {
        GetTimelineTweets getTimelineTweets = request.getRetrieveTweetsObject(twitterImplement);
        return getTimelineTweets.fetchLatestTweets();
    }


    @GET
    @Path("/healthCheck")
    public String healthCheck() {
        return "Ping Received at " + new Date();
    }

    @POST
    @Path("/tweetAgain")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendTweet(Request request) throws TwitterException {
        logger.info("got into post");
        String post = request.getMessage();
        if (StringUtil.isEmpty(post)) {
            logger.error("error happened");
            return Response.status(400, "Please enter valid tweet").build();
        } else {
            PostTweet postTweet = request.getSendTweetObject(twitterImplement);
            Status status = postTweet.sendTweet(post);
            if (status.getText().equals(post)) {
                logger.info("successfully posted");
                return Response.status(200, "Request is successful").build();
            } else {
                logger.error("internal error occurred");
                return Response.status(500, "internal server error").build();
            }
        }

    }

}