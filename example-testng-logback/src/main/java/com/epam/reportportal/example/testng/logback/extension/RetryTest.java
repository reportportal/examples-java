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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class RetryTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(RetryTest.class);

	private static final AtomicInteger COUNTER = new AtomicInteger(0);

	@Test(retryAnalyzer = RetryImpl.class)
	public void failOne() {
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
