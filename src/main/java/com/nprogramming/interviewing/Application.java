package com.nprogramming.interviewing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.invoke.MethodHandles;

@SpringBootApplication
public class Application {

//    private static final Logger log =
//            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired(required = false)
    private DatabaseLoader databaseLoader;
}
