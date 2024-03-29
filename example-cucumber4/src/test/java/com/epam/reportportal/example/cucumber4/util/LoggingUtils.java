package com.epam.reportportal.example.cucumber4.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Base64;

/**
 * Useful utility class for binary data logging (e.g. sending files to ReportPortal). You can disable console output for
 * `binary_data_logger` to avoid redundant verbose output.
 */
public class LoggingUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger("binary_data_logger");

	private LoggingUtils() {
		//statics only
	}

	public static void log(File file, String message) {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", file.getAbsolutePath(), message);
	}

	public static void logBase64(String base64, String message) {
		LOGGER.info("RP_MESSAGE#BASE64#{}#{}", base64, message);
	}

	public static void log(byte[] bytes, String message) {
		logBase64(Base64.getEncoder().encodeToString(bytes), message);
	}
}
