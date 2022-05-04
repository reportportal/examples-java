package com.epam.reportportal.example.junit.logging;

import com.epam.reportportal.example.junit.LoggingUtils;
import com.epam.reportportal.service.ReportPortal;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		ReportPortal.emitLog("LAUNCH LOG MESAGE", "error", new Date());

		File file = File.createTempFile("rp-test", ".css");
		Resources.asByteSource(Resources.getResource("files/file.css")).copyTo(Files.asByteSink(file));
		ReportPortal.emitLog("LAUNCH LOG MESAGE WITH ATTACHMENT", "error", new Date(), file);

		LoggingUtils.log(Resources.asByteSource(Resources.getResource(JSON_FILE_PATH)).read(), "I'm logging content via BASE64");
	}

	@Test
	public void logJsonFile() throws IOException {
		/* here we are logging some binary data as file (useful for selenium) */
		File file = File.createTempFile("rp-test", ".json");
		Resources.asByteSource(Resources.getResource(JSON_FILE_PATH)).copyTo(Files.asByteSink(file));

		for (int i = 0; i < 1; i++) {
			LOGGER.info("RP_MESSAGE#FILE#{}#{}", file.getAbsolutePath(), "I'm logging content via temp file");
		}
	}

}
