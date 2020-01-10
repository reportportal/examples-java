package com.epam.reportportal.example.junit5;


import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rp.com.google.common.io.Files;
import rp.com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Unit test for simple App.
 */
public class DummyTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(DummyTest.class);

    private static final AtomicInteger BEFORE_COUNTER = new AtomicInteger();
    private static final AtomicInteger AFTER_COUNTER = new AtomicInteger();

    @BeforeEach
    public void beforeClass() throws InterruptedException {
        LOGGER.info("Inside Dummy beforeEach"+ BEFORE_COUNTER.incrementAndGet());
        Thread.sleep(100);
    }

    @Test
    public void test1() throws IOException {
        LOGGER.info("Inside Dummy test 1");
        // Report launch log
        File file = File.createTempFile("rp-test", ".xml");
        Resources.asByteSource(Resources.getResource("logback.xml")).copyTo(Files.asByteSink(file));
        int n = 5;
        while (n-- > 0) {
            LoggingUtils.log(file, "LAUNCH LOG MESSAGE WITH ATTACHMENT");
        }

        assertTrue(true);
    }

    @Test
    public void test2() {
        LOGGER.info("Dummy test 2 started");
        assertTrue(true);
    }

    @AfterEach
    public void after() throws InterruptedException {
        LOGGER.info("Inside Dummy afterEach " + AFTER_COUNTER.incrementAndGet());
        Thread.sleep(100);
    }
 }
