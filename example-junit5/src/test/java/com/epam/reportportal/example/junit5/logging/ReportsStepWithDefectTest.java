package com.epam.reportportal.example.junit5.logging;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReportsStepWithDefectTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportsStepWithDefectTest.class);

    @Test
    @Disabled("for demonstration purposes")
    @DisplayName("DisabledTest")
    public void testSkipped() {
        LOGGER.info("I'm disabled test");
    }

    @Test
    public void testFailure() {
        assertEquals(2, 1);
    }

    @Test
    public void testFailureWithCustomMessage() {
        assertEquals(2, 1, "Failure msg");
    }

    @Test
    public void expectedFailureAbsent() {
        assertThrows(AssertionError.class, () -> assertEquals(1, 1));
    }
}
