package com.epam.reportportal.example.junit;

import com.epam.reportportal.service.ReportPortal;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class DummyTest {

	@BeforeClass
	public static void beforeClass() throws InterruptedException {
		Thread.sleep(100);
	}

	@Before
	public void before() throws InterruptedException {
		Thread.sleep(100);
	}

	@Test
	public void test1() throws InterruptedException, IOException {
		// Report launch log
		File file = File.createTempFile("rp-test", ".css");
		Resources.asByteSource(Resources.getResource("logback.xml")).copyTo(Files.asByteSink(file));
		ReportPortal.emitLog("LAUNCH LOG MESAGE WITH ATTACHMENT", "error", new Date(), file);

		Thread.sleep(100);
		assertTrue(true);
	}

	@Test
	public void test2() throws InterruptedException {
		Thread.sleep(100);
		assertTrue(true);
	}

	@After
	public void after() throws InterruptedException {
		Thread.sleep(100);
	}

	@AfterClass
	public static void afterClass() throws InterruptedException {
		Thread.sleep(100);
	}

}
