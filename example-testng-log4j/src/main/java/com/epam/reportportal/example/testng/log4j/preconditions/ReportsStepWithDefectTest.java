package com.epam.reportportal.example.testng.log4j.preconditions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class ReportsStepWithDefectTest {

	private static final Logger LOGGER = LogManager.getLogger(ReportsStepWithDefectTest.class);

	@Test
	@Ignore("for demonstration purposes")
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
