package com.epam.reportportal.example.junit5.logging;

import com.epam.reportportal.example.junit5.util.AttachmentHelper;
import com.epam.reportportal.example.junit5.util.LoggingUtils;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AfterAndBeforeMethodTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(AfterAndBeforeMethodTest.class);

	@BeforeAll
	public void beforeAll() {
		LOGGER.info("Inside AfterAndBeforeMethodTest beforeAll ");
	}

	@BeforeEach
	public void beforeEach() {
		LOGGER.info("Inside AfterAndBeforeMethodTest beforeEach ");
	}

	@RepeatedTest(3)
	public void test1() {
		LOGGER.info("Inside AfterAndBeforeMethodTest test ");
		// Report launch log
		File file = AttachmentHelper.getFileFromResources("src/test/resources", "logback", "xml");
		int n = 5;
		while (n-- > 0) {
			LoggingUtils.log(file, "LAUNCH LOG MESSAGE WITH ATTACHMENT");
		}

		assertTrue(true);
	}

	@AfterAll
	public void afterAll() {
		LOGGER.info("Inside AfterAndBeforeMethodTest afterAll");
	}

	@AfterEach
	public void afterEach() {
		LOGGER.info("Inside AfterAndBeforeMethodTest afterEach ");
	}
}
