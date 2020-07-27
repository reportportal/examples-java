package com.epam.reportportal.example.junit.logging;

import com.epam.reportportal.example.junit.LoggingUtils;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class AfterAndBeforeMethodTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(AfterAndBeforeMethodTest.class);

	@BeforeClass
	public static void beforeClass() throws Exception {
		LOGGER.info("Inside AfterAndBeforeMethodTest beforeClass ");
	}

	@Before
	public void before() {
		LOGGER.info("Inside AfterAndBeforeMethodTest beforeEach ");
	}

	@Test
	public void test1() throws IOException {
		LOGGER.info("Inside AfterAndBeforeMethodTest test ");
		// Report launch log
		File file = File.createTempFile("rp-test", ".xml");
		Resources.asByteSource(Resources.getResource("logback.xml")).copyTo(Files.asByteSink(file));
		int n = 5;
		while (n-- > 0) {
			LoggingUtils.log(file, "LAUNCH LOG MESSAGE WITH ATTACHMENT");
		}

		assertTrue(true);
	}

	@AfterClass
	public static void afterClass() throws InterruptedException {
		LOGGER.info("Inside AfterAndBeforeMethodTest afterClass");
	}

	@After
	public void after() {
		LOGGER.info("Inside AfterAndBeforeMethodTest after");
	}
}