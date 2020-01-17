package com.epam.reportportal.example.junit5;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogTest.class);

	@Test
	void LogTest() {
		LOGGER.trace("Trace level log");
		LOGGER.debug("Debug level log");
		LOGGER.info("Info level log");
		LOGGER.warn("Warn level log");
		LOGGER.error("Error level log");
		throw new IllegalArgumentException("Critical exception");
	}
}
