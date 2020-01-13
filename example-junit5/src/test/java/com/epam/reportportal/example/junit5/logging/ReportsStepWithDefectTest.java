package com.epam.reportportal.example.junit5.logging;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.opentest4j.TestAbortedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReportsStepWithDefectTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportAttachmentsTest.class);

    @Test()
    @Disabled
    @DisplayName("DisabledTest")
    public void repeatedTest() {
        LOGGER.info("I'm disabled test");
    }

    @Test
    public void testSkipped() {
        throw new TestAbortedException();
    }

    @Test
    public void testFailure() {
        assertEquals(2, 1);
    }

    @Test
    public void testFailureWithCustomMessage() {
        assertEquals(2, 1, "Failure msg");
    }

    @Test()
    public void expectedFailureAbsent() {
        assertThrows(AssertionError.class, () -> assertEquals(1, 1));
    }
}
