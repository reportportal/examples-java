package com.epam.reportportal.example.junit5;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExpectToFail {

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
