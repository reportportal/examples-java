/*
 * Copyright 2020 EPAM Systems
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.epam.reportportal.example.testng.logback.extension;

import com.epam.reportportal.testng.BaseTestNGListener;
import com.epam.reportportal.testng.ITestNGService;
import com.epam.reportportal.testng.TestNGService;
import com.epam.reportportal.utils.MemoizingSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import static java.util.Optional.ofNullable;

/**
 * Extension example. The test publishes retry reason into skipped a test as a log entry.
 */
@Listeners(RetriedTestReasonReporting.ExtendedListener.class)
public class RetriedTestReasonReporting {

	private static final Logger LOGGER = LoggerFactory.getLogger(RetriedTestReasonReporting.class);

	private static final int MAXIMUM_RETRIES = 1;

	private static final AtomicInteger COUNTER = new AtomicInteger(0);

	@Test(retryAnalyzer = RetryImpl.class)
	public void failOne() {
		int retry = COUNTER.incrementAndGet();
		if (retry <= MAXIMUM_RETRIES) {
			LOGGER.warn("Failed attempt: " + retry);
			Assert.fail();
		}
		LOGGER.info("Success attempt");
	}

	public static class RetryImpl implements IRetryAnalyzer {
		private final AtomicInteger retryNumber = new AtomicInteger();

		@Override
		public boolean retry(ITestResult result) {
			int retry = retryNumber.incrementAndGet();
			LOGGER.info("Retry attempt: " + retry);
			return retry <= MAXIMUM_RETRIES;
		}
	}

	public static class ExtendedListener extends BaseTestNGListener {
		public static final Supplier<ITestNGService> SERVICE = new MemoizingSupplier<>(SkipReasonLoggingService::new);

		public ExtendedListener() {
			super(SERVICE.get());
		}
	}

	public static class SkipReasonLoggingService extends TestNGService {
		@Override
		protected void createSkippedSteps(ITestResult testResult) {
			ofNullable(testResult.getThrowable()).ifPresent(t -> LOGGER.error("Test failed with: ", t));
		}
	}
}
