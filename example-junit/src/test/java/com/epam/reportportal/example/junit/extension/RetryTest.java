package com.epam.reportportal.example.junit.extension;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class RetryTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(RetryTest.class);

	private static final int RETRY_NUMBER = 20;
	private static final AtomicInteger COUNTER = new AtomicInteger(0);

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
