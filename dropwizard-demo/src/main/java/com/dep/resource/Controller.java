package com.dep.resource;

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
import java.util.List;


@Produces(MediaType.APPLICATION_JSON)
@Path("/api/1.0/twitter")
public class Controller {

    public static final Logger logger = LoggerFactory.getLogger(Controller.class);
    static TwitterImplement twitterImplement;

    public Controller(TwitterImplement twitterImplement) {
        this.twitterImplement = twitterImplement;
    }

    public Controller() {
        twitterImplement = new TwitterImplement();
    }

    @GET
    @Path("GetTweets")
    public static Response fetchTweets() {
        List<String> tweets;
        tweets = twitterImplement.fetchLatestTweets();
        return Response.ok(tweets).build();
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
            Status status = twitterImplement.sendTweets(post);
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