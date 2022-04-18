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
import com.epam.reportportal.utils.files.Utils;
import com.google.common.io.Files;
import org.awaitility.Awaitility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
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
	public void logCss() throws IOException {
		File file = File.createTempFile("rp-test", ".css");
		Utils.getFile(new File("files/css.css")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging CSS");
	}

	@Test
	public void logHtml() throws IOException {
		File file = File.createTempFile("rp-test", ".html");
		Utils.getFile(new File("files/html.html")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging HTML");
	}

	@Test
	public void logPdf() throws IOException {
		File file = File.createTempFile("rp-test", ".pdf");
		Utils.getFile(new File("files/test.pdf")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging PDF");
	}

	@Test
	public void logZip() throws IOException {
		File file = File.createTempFile("rp-test", ".zip");
		Utils.getFile(new File("files/demo.zip")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging ZIP");
	}

	@Test
	public void logJavascript() throws IOException {
		File file = File.createTempFile("rp-test", ".js");
		Utils.getFile(new File("files/javascript.js")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging JS");
	}

	@Test
	public void logPhp() throws IOException {
		File file = File.createTempFile("rp-test", ".php");
		Utils.getFile(new File("files/php.php")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging php");
	}

	@Test
	public void logPlain() throws IOException {
		File file = File.createTempFile("rp-test", ".txt");
		Utils.getFile(new File("files/plain.txt")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging txt");
	}

	@Test
	public void logCsv() throws IOException {
		File file = File.createTempFile("rp-test", ".csv");
		Utils.getFile(new File("files/Test.csv")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging txt");
	}

	@Test
	public void logCmd() throws IOException {
		File file = File.createTempFile("rp-test", ".cmd");
		Utils.getFile(new File("files/Test.cmd")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging txt");
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
