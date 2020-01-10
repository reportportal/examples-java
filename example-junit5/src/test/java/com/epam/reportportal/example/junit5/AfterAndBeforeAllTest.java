package com.epam.reportportal.example.junit5;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rp.com.google.common.io.Files;
import rp.com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AfterAndBeforeAllTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DummyTest.class);

    @BeforeAll
    public void before() throws InterruptedException {
        LOGGER.info("Inside Dummy beforeAll ");
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

    @AfterAll
    public void afterClass() throws InterruptedException {
        LOGGER.info("Inside Dummy afterAll");
        Thread.sleep(100);
    }
}
