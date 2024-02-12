package com.epam.reportportal.example.junit.logging;

import com.epam.reportportal.example.junit.LoggingUtils;
import com.epam.reportportal.utils.files.ByteSource;
import com.epam.reportportal.utils.files.Utils;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
	public void test1() throws IOException {
		LOGGER.info("Inside AfterAndBeforeMethodTest test ");
		// Report launch log
		File file = File.createTempFile("rp-test", ".xml");
		ByteSource source = Utils.getFileAsByteSource(new File("logback.xml"));
		try (InputStream is = source.openStream()) {
			try (OutputStream os = java.nio.file.Files.newOutputStream(file.toPath())) {
				Utils.copyStreams(is, os);
			}
		}
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