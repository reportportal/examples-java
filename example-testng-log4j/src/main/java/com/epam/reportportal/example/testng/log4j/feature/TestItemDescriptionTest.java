package com.epam.reportportal.example.testng.log4j.feature;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class TestItemDescriptionTest {
	private static final Logger LOGGER = LogManager.getLogger(TestItemDescriptionTest.class);

	@Test(description = "A test step description to show on Report Portal")
	public void testLaunchAndItemDescription() {
		LOGGER.info("A test step log entry.");
	}
}
