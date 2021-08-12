package com.epam.reportportal.example.junit5.logging;

import com.epam.reportportal.example.junit5.LoggingUtils;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.io.Files;
import com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AfterAndBeforeMethodTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AfterAndBeforeMethodTest.class);

    @BeforeAll
    public void beforeAll() throws InterruptedException {
        LOGGER.info("Inside AfterAndBeforeMethodTest beforeAll ");
    }

    @BeforeEach
    public void beforeEach() {
        LOGGER.info("Inside AfterAndBeforeMethodTest beforeEach ");
    }

    @RepeatedTest(3)
    public void test1() throws IOException {
        LOGGER.info("Inside AfterAndBeforeMethodTest test ");
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
    public void afterAll() throws InterruptedException {
        LOGGER.info("Inside AfterAndBeforeMethodTest afterAll");
    }

    @AfterEach
    public void afterEach() {
        LOGGER.info("Inside AfterAndBeforeMethodTest afterEach ");
    }
}
