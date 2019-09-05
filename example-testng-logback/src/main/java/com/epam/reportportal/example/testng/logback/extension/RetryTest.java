package com.epam.reportportal.example.testng.logback.extension;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class RetryTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(RetryTest.class);

	private static final AtomicInteger COUNTER = new AtomicInteger(0);

	@Test(retryAnalyzer = RetryImpl.class)
	public void failOne() throws IOException, InterruptedException {
		String errorMsg = "Ooops";
		if (20 != COUNTER.get()) {
			for (int i = 0; i < 10; i++) {
				LOGGER.error(errorMsg);
			}
			Assert.fail(errorMsg);
		}

	}

	public static class RetryImpl implements IRetryAnalyzer {

		@Override
		public boolean retry(ITestResult result) {
			return !result.isSuccess() && COUNTER.incrementAndGet() < 50;
		}
	}
}
