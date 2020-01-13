package com.epam.reportportal.example.junit5.logging;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportsRepeatedTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportAttachmentsTest.class);

    @RepeatedTest(10)
    @DisplayName("RepeatedTest")
    public void repeatedTest() {
        LOGGER.info("I'm repeated test");
    }
}
