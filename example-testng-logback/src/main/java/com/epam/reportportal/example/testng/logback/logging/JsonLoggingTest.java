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

package com.epam.reportportal.example.testng.logback.logging;

import com.epam.reportportal.example.testng.logback.LoggingUtils;
import com.epam.reportportal.service.ReportPortal;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * JUST an example of file logging
 *
 * @author Andrei Varabyeu
 */
public class JsonLoggingTest {

	public static final String JSON_FILE_PATH = "xml/file.json";
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonLoggingTest.class);

	@Test
	public void logJsonBase64() throws IOException {
		/* here we are logging some binary data as BASE64 string */
		ReportPortal.emitLaunchLog("LAUNCH LOG MESAGE", "error", new Date());

		File file = File.createTempFile("rp-test", ".css");
		Resources.asByteSource(Resources.getResource("files/css.css")).copyTo(Files.asByteSink(file));
		ReportPortal.emitLaunchLog("LAUNCH LOG MESAGE WITH ATTACHMENT", "error", new Date(), file);

		LoggingUtils.log(Resources.asByteSource(Resources.getResource(JSON_FILE_PATH)).read(), "I'm logging content via BASE64");
	}

	@Test
	public void logJsonFile() throws IOException, InterruptedException {
		/* here we are logging some binary data as file (useful for selenium) */
		File file = File.createTempFile("rp-test", ".json");
		Resources.asByteSource(Resources.getResource(JSON_FILE_PATH)).copyTo(Files.asByteSink(file));

		for (int i = 0; i < 1; i++) {
			LoggingUtils.log(file, "I'm logging content via temp file");
		}
	}
}
