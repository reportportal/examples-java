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

package com.epam.reportportal.example.testng.logback.bugs;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.ITestAnnotation;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * In case of failed {@link BeforeClass} and retry analyzed in a test, it should be reported correctly (test should be skipped).
 * <br />
 * Source: <a href="https://github.com/reportportal/agent-java-testNG/issues/27">Issue #27</a>
 */
@Listeners({ReproduceItemIdTest.RetryAnnotationListener.class, ReportPortalTestNGListener.class })
public class ReproduceItemIdTest {
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@BeforeClass
	public void bcReproduceItemIdTest() {
		logger.info("bcReproduceItemIdTest");
		Assert.fail(); // requirement: fail at non @Test method
	}

	@Test
	public void test0() {
		logger.info("test0");
		Assert.assertTrue(true);
	}

	public static class RetryAnalyzer implements IRetryAnalyzer {
		private final Logger logger = LoggerFactory.getLogger(this.getClass());

		int counter = 0;
		int retryLimit = 2;

		@Override
		public boolean retry(ITestResult result) {
			if (result.isSuccess()) {
				return false;
			}
			if (counter < retryLimit) {
				if (result.getThrowable() != null) {
					logger.warn(
							String.format("Test %s failed with exception. Will retry... (%s attempt)", result.getName(), counter),
							result.getThrowable()
					);
				}
				counter++;
				return true;
			}
			return false;
		}
	}

	/**
	 * This class is a Listener. It will apply retry mechanism (RetryAnalyzer class) for every test method which doesn't have RetryAnalyzer specified at annotation
	 */
	public static class RetryAnnotationListener implements IAnnotationTransformer {

		@Override
		public void transform(ITestAnnotation testAnnotation, Class testClass, Constructor testConstructor, Method testMethod) {
			Class<? extends IRetryAnalyzer> retry = testAnnotation.getRetryAnalyzerClass();

			if (retry == null) {
				testAnnotation.setRetryAnalyzer(RetryAnalyzer.class);
			}
		}
	}
}
