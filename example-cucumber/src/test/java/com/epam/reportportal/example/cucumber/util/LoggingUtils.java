package com.epam.reportportal.example.cucumber.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

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
}
