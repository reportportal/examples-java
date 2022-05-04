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

package com.epam.reportportal.example.testng.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Base64;

/**
 * Useful for binary data (e.g. sending files to ReportPortal)
 *
 * @author Andrei Varabyeu
 */
public class LoggingUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger("binary_data_logger");

	private LoggingUtils() {
		//statics only
	}

	public static void log(File file, String message) {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", file.getAbsolutePath(), message);
	}

	public static void log(byte[] bytes, String message) {
		LOGGER.info("RP_MESSAGE#BASE64#{}#{}", Base64.getEncoder().encodeToString(bytes), message);
	}

	public static void logBase64(String base64, String message) {
		LOGGER.info("RP_MESSAGE#BASE64#{}#{}", base64, message);
	}
}
