package com.epam.reportportal.example.testng.logback.feature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class TestItemDescriptionTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestItemDescriptionTest.class);

	@Test(description = "A test step description to show on Report Portal")
	public void testLaunchAndItemDescription() {
		LOGGER.info("A test step log entry.");
	}
}
