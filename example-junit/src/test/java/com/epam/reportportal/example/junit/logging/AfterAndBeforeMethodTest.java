package com.epam.reportportal.example.junit.logging;

import com.epam.reportportal.example.junit.util.AttachmentHelper;
import com.epam.reportportal.example.junit.util.LoggingUtils;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class AfterAndBeforeMethodTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(AfterAndBeforeMethodTest.class);

	@BeforeClass
	public static void beforeClass() {
		LOGGER.info("Inside AfterAndBeforeMethodTest beforeClass ");
	}

	@Before
	public void before() {
		LOGGER.info("Inside AfterAndBeforeMethodTest beforeEach ");
	}

	@Test
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

	@AfterClass
	public static void afterClass() {
		LOGGER.info("Inside AfterAndBeforeMethodTest afterClass");
	}

	@After
	public void after() {
		LOGGER.info("Inside AfterAndBeforeMethodTest after");
	}
}