package com.epam.reportportal.example.junit.extension;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nordstrom.automation.junit.JUnitConfig.JUnitSettings;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.atomic.AtomicInteger;

@Ignore
public class RetryTest {

	private static int count;
	private static final Logger LOGGER = LoggerFactory.getLogger(RetryTest.class);

	private static final int RETRY_NUMBER = 20;
	private static final AtomicInteger COUNTER = new AtomicInteger(0);

	@BeforeClass
	public static void beforeClass() {
		System.setProperty(JUnitSettings.MAX_RETRY.key(), "3");
	}

	@Test
	public void testPassOnRetry() {
		System.out.println("testPassOnRetry: " + count);
		assertTrue("testPassOnRetry: " + count, count++ > 0);
	}

	@Ignore
	@Test
	public void failOne() {
		String errorMsg = "Ooops";
		if (RETRY_NUMBER > COUNTER.incrementAndGet()) {
			for (int i = 0; i < 10; i++) {
				LOGGER.error(errorMsg);
			}
			Assert.fail(errorMsg);
		}
		LOGGER.info("Test passed!");
	}
}
