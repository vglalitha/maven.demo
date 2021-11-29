package com.dep.resource;

import com.dep.Services.TwitterImplement;
import com.dep.models.SendResponse;
import com.dep.models.TweetResponse;
import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.Status;
import twitter4j.TwitterException;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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

    @RequestMapping(method = RequestMethod.GET, value = "/getTweets")
    public List<TweetResponse> fetchTweets() {
        return twitterImplement.fetchLatestTweets();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/filteredTweets")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<TweetResponse> filteredTweets(@QueryParam("searchKey") String searchKey) {
        return twitterImplement.getFilteredTweets(searchKey);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getPage")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<TweetResponse> getPage(@QueryParam("start") int start, @QueryParam("size") int size) throws TwitterException {
        return twitterImplement.getpage(start, size);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/healthCheck")
    public String healthCheck() {
        return "Ping Received at " + new Date();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tweetAgain")
    public SendResponse sendTweet(@RequestBody Request request) throws TwitterException {
            logger.info("got into post");
            String post = request.getMessage();
            if (StringUtil.isEmpty(post)) {
                logger.error("error happened");
                return new SendResponse("Please enter valid tweet", 400);
            } else {
                Status status = twitterImplement.sendTweets(post);
                if (status.getText().equals(post)) {
                    logger.info("successfully posted");
                    return new SendResponse("Tweet posted Successfully", 200);
                } else {
                    logger.error("internal error occurred");
                    return new SendResponse("Request is incomplete", 500);
                }
            }
    }
}