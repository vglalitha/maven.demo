package com.dep.resource;

import com.dep.Services.TwitterImplement;
import com.dep.models.SendResponse;
import com.dep.models.TweetResponse;
import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import twitter4j.Status;
import twitter4j.TwitterException;

import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@RestController
public class Controller {

    public static final Logger Logger = LoggerFactory.getLogger(Controller.class);
    @Autowired
    TwitterImplement twitterImplement;

    @RequestMapping(method = RequestMethod.GET, value = "/getTweets")
    public List<TweetResponse> fetchTweets() {
        return twitterImplement.fetchLatestTweets();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/filteredTweets")
    public List<TweetResponse> filteredTweets(@QueryParam("searchKey") String searchKey) {
        return twitterImplement.getFilteredTweets(searchKey);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getPage")
    public List<TweetResponse> getPage(@QueryParam("start") int start, @QueryParam("size") int size) throws TwitterException {
        return twitterImplement.getPage(start, size);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/healthCheck")
    public String healthCheck() {
        return "Ping Received at " + new Date();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tweetAgain")
    public ResponseEntity<SendResponse> sendTweet(@RequestBody Request request) throws TwitterException {
        Logger.info("got into post");
        String post = request.getMessage();
        HttpHeaders responseHeaders = new HttpHeaders();
        if (StringUtil.isEmpty(post)) {
            Logger.error("error happened");
            return new ResponseEntity(new SendResponse("Invalid!,please enter a valid tweet"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        } else {
            Status status = twitterImplement.sendTweets(post);
            if (status.getText().equals(post)) {
                Logger.info("successfully posted");
                return new ResponseEntity(new SendResponse("Tweet successfully posted"), new HttpHeaders(), HttpStatus.OK);
            } else {
                Logger.error("internal error occurred");
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Request is incomplete");
            }
        }
    }
}