package com.epam.reportportal.example.junit.extension;

import com.nordstrom.automation.junit.JUnitConfig.JUnitSettings;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class RetryTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(RetryTest.class);

	private static final int RETRY_NUMBER = 2;
	private static final AtomicInteger COUNTER = new AtomicInteger(0);

	@BeforeClass
	public static void beforeClass() {
		System.setProperty(JUnitSettings.MAX_RETRY.key(), "3");
	}

	@AfterClass
	public static void afterClass() {
		System.clearProperty(JUnitSettings.MAX_RETRY.key());
	}

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
