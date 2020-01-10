package com.epam.reportportal.example.junit5;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FailedTest {

    @Test()
    public void expectedFailureThrown() {
        assertThrows(AssertionError.class, () -> assertEquals(2, 1));
    }

    @Test
    @Disabled
    public void testIgnore() {
        assertEquals(1, 1);
    }
}
