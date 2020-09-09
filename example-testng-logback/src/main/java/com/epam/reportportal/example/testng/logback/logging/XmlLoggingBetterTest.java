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
import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * IMPROVED version of file logging example
 * Uses external utilities
 *
 * @author Andrei Varabyeu
 */
public class XmlLoggingBetterTest {

	public static final String XML_FILE_PATH = "xml/file.xml";

	@Test
	public void logXmlBase64() throws IOException {
		/* here we are logging some binary data as BASE64 string */
		LoggingUtils.log(Resources.asByteSource(Resources.getResource(XML_FILE_PATH)).read(), "I'm logging content via BASE64");
	}

	@Test
	public void logXmlFile() throws IOException {
		/* here we are logging some binary data as file (useful for selenium) */
		File file = File.createTempFile("rp-test", "xml");
		Resources.asByteSource(Resources.getResource(XML_FILE_PATH)).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging content via BASE64");
	}
}
