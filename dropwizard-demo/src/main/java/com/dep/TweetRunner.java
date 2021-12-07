package com.dep;


import com.dep.config.BRSConfiguration;
import com.dep.resource.Controller;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication(scanBasePackages = {"com.dep.resource", "com.dep.Services", "com.dep.config"})
public class TweetRunner extends Application<BRSConfiguration> {
    BRSConfiguration brsConfiguration;
    Environment environment;

    public TweetRunner(BRSConfiguration brsConfiguration, Environment environment) {
        this.brsConfiguration = brsConfiguration;
        this.environment = environment;
    }

    public TweetRunner() {
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TweetRunner.class, args);
    }

    @Override
    public void run(BRSConfiguration brsConfiguration, Environment environment) throws Exception {
        environment.jersey().register(new Controller());
    }

}
