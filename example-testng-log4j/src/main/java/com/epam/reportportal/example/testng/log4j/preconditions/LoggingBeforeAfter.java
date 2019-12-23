package com.epam.reportportal.example.testng.log4j.preconditions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

public class LoggingBeforeAfter {
	private static final Logger LOGGER = LogManager.getLogger(LoggingBeforeAfter.class);

	@BeforeClass(description = "@BeforeClass description")
	public void beforeClassLog() {
		LOGGER.info("@BeforeClass log");
	}

	@BeforeMethod(description = "@BeforeMethod description")
	public void beforeMethodLog() {
		LOGGER.info("@BeforeMethod log");
	}

	@Test
	public void testLog() {
		LOGGER.info("@Test log");
	}

	@AfterMethod(description = "@AfterMethod description")
	public void afterMethodLog() {
		LOGGER.info("@AfterMethod log");
	}

	@AfterClass(description = "@AfterClass description")
	public void afterClassLog() {
		LOGGER.info("@AfterClass log");
	}
}
