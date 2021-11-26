package com.dep.resource;

import com.dep.Services.TwitterImplement;
import com.dep.models.TweetResponse;
import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import twitter4j.Status;
import twitter4j.TwitterException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@RestController
public class Controller {

    public static final Logger logger = LoggerFactory.getLogger(Controller.class);
    @Autowired
    TwitterImplement twitterImplement;

    @Bean
    public TwitterImplement getService(){
        return new TwitterImplement();
    }


    @RequestMapping(value = "getTweets",method = RequestMethod.GET)
    public Response fetchTweets() {
        return Response.ok(twitterImplement.fetchLatestTweets()).build();
    }

    @RequestMapping("/filteredTweets/{searchKey}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response filteredTweets(@PathVariable String searchKey) throws TwitterException {
        List<TweetResponse> response = twitterImplement.getFilteredTweets(searchKey);
        return Response.ok(response).build();
    }

    @RequestMapping("/getPage/start/{start}/size/{size}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getpage(@PathVariable int start, @PathVariable int size) throws TwitterException {
        List<TweetResponse> response = twitterImplement.getpage(start, size);
        return Response.ok(response).build();}


    @RequestMapping("/healthCheck")
    public String healthCheck() {
        return "Ping Received at " + new Date();
    }


    @RequestMapping(method = RequestMethod.POST, value = "/tweetAgain")
    public Response sendTweet(@RequestBody Request request) throws TwitterException {
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