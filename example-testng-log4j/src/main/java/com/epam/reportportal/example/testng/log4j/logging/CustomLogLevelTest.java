/*
 * Copyright 2025 EPAM Systems
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.epam.reportportal.example.testng.log4j.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

/**
 * The test logs a message with CUSTOM log level to ReportPortal.
 */
public class CustomLogLevelTest {
	private static final Logger LOGGER = LogManager.getLogger(CustomLogLevelTest.class);

	@Test
	public void logWithCustomLogLevel() {
		LOGGER.log(Level.forName("CUSTOM", 35000), "Custom Log Level message");
	}
}
