package com.dep.resource;

import com.dep.tweeet;
import org.eclipse.jetty.util.StringUtil;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Path("/api/1.0/twitter")
public class DropResource {

    @GET
    @Path("/healthCheck")
    public String healthCheck(){

        return "Ping Received at " + new Date();
    }

    @POST
    @Path("/tweetAgain")
    public Response tweetAgain(String post) throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        String ret=null;
        if(StringUtil.isEmpty(post)) {
            return Response.status(400,"Please enter valid tweet").build();
        }
        else{
            twitter.updateStatus(post);
            return Response.status(200,"successfully tweeted").build();
        }

    }

    @GET
    @Path("/getTweets")
    public String[] time () throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        List<Status> status = twitter.getHomeTimeline();
        int size = status.size();
        String str[] = new String[size];
        int i = 0;
        for (Status st : status) {
            str[i] = st.getUser().getName() + "-------" + st.getText();
            i++;
        }
        return str;
    }
}
