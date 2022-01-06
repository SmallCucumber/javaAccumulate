package com.zmm.springboot.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(OrderRunner1.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("The Runner start to initialize ...");
    }
}