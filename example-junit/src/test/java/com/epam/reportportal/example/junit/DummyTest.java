package com.epam.reportportal.example.junit;

import com.epam.reportportal.service.ReportPortal;
import com.epam.reportportal.utils.files.ByteSource;
import com.epam.reportportal.utils.files.Utils;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class DummyTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(DummyTest.class);

	private static final AtomicInteger BEFORE_COUNTER = new AtomicInteger();
	private static final AtomicInteger AFTER_COUNTER = new AtomicInteger();

	@BeforeClass
	public static void beforeClass() throws InterruptedException {
		LOGGER.info("Inside Dummy beforeClass");
		Thread.sleep(100);
	}

	@Before
	public void before() throws InterruptedException {
		LOGGER.info("Inside Dummy before " + BEFORE_COUNTER.incrementAndGet());
		Thread.sleep(100);
	}

	@Test
	public void test1() throws IOException {
		LOGGER.info("Inside Dummy test 1");
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
			LOGGER.info("RP_MESSAGE#FILE#{}#{}", file.getAbsolutePath(), "LAUNCH LOG MESSAGE WITH ATTACHMENT");
		}
		ReportPortal.emitLaunchLog("LAUNCH LOG MESSAGE WITH ATTACHMENT", "error", new Date(), file);

		assertTrue(true);
	}

	@Test
	public void test2() {
		LOGGER.info("Dummy test 2 started");
		assertTrue(true);
	}

	@After
	public void after() throws InterruptedException {
		LOGGER.info("Inside Dummy after " + AFTER_COUNTER.incrementAndGet());
		Thread.sleep(100);
	}

	@AfterClass
	public static void afterClass() throws InterruptedException {
		LOGGER.info("Inside Dummy afterClass");
		Thread.sleep(100);
	}

}
