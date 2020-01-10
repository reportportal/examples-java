package com.epam.reportportal.example.junit5.extension;

import org.junit.jupiter.api.RepeatedTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class RetryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RetryTest.class);

    private static final int RETRY_NUMBER = 20;
    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    @RepeatedTest(10)
    public void failOne() {
        LOGGER.info("Test passed!");
    }
}
