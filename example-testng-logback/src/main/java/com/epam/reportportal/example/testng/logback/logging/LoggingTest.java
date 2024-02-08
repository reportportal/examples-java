/*
 * Copyright 2022 EPAM Systems
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

package com.epam.reportportal.example.testng.logback.logging;

import com.epam.reportportal.example.testng.logback.LoggingUtils;
import org.awaitility.Awaitility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * Different logging types and options example test
 */
public class LoggingTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingTest.class);

	@Test
	public void logCss() {
		LoggingUtils.log(new File("files/css.css"), "I'm logging CSS");
	}

	@Test
	public void logHtml() {
		LoggingUtils.log(new File("files/html.html"), "I'm logging HTML");
	}

	@Test
	public void logPdf() {
		LoggingUtils.log(new File("files/test.pdf"), "I'm logging PDF");
	}

	@Test
	public void logZip() {
		LoggingUtils.log(new File("files/demo.zip"), "I'm logging ZIP");
	}

	@Test
	public void logJavascript() {
		LoggingUtils.log(new File("files/javascript.js"), "I'm logging JS");
	}

	@Test
	public void logPhp() {
		LoggingUtils.log(new File("files/php.php"), "I'm logging php");
	}

	@Test
	public void logPlain() {
		LoggingUtils.log(new File("files/plain.txt"), "I'm logging txt");
	}

	@Test
	public void logCsv() {
		LoggingUtils.log(new File("files/Test.csv"), "I'm logging txt");
	}

	@Test
	public void logCmd() {
		LoggingUtils.log(new File("files/Test.cmd"), "I'm logging txt");
	}

	@Test(timeOut = 100)
	public void logInChildThread() {
		LOGGER.info("I'm logging in a test with timeout");
	}

	@Test
	public void logInAwaitilityThread() {
		AtomicInteger counter = new AtomicInteger();
		Awaitility.await("Logging inside Awaitility")
				.atMost(Duration.of(1, ChronoUnit.SECONDS))
				.pollDelay(Duration.ZERO)
				.pollInterval(Duration.ofMillis(200))
				.until(() -> {
					int count = counter.incrementAndGet();
					LOGGER.info("Inside Awaitility " + count);
					return count;
				}, greaterThanOrEqualTo(4));
	}
}
