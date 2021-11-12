package com.dep;


import com.dep.config.BRSConfiguration;
import com.dep.resource.Controller;
import com.dep.resource.Request;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

import static org.slf4j.LoggerFactory.*;

public class tweeet extends Application<BRSConfiguration> {
    BRSConfiguration brsConfiguration;
    Environment environment;
    public tweeet(BRSConfiguration brsConfiguration, Environment environment)
    {
        this.brsConfiguration=brsConfiguration;
        this.environment=environment;
    }
    public tweeet(){}
    public static void main(String[] args) throws Exception {
        new tweeet().run(args);
    }


    @Override
    public void run(BRSConfiguration brsConfiguration, Environment environment) throws Exception {
        environment.jersey().register(new Controller());

    }

}
