package com.epam.reportportal.example.junit5;

import com.epam.reportportal.annotations.TestCaseId;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCaseIdTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestCaseIdTest.class);

	@Test
	@TestCaseId("junit5-test")
	void testCaseIdTest() {
		LOGGER.info("test case id test");
	}
}
