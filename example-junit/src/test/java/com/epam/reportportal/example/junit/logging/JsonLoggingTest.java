package com.epam.reportportal.example.junit.logging;

import com.epam.reportportal.example.junit.LoggingUtils;
import com.epam.reportportal.service.ReportPortal;
import com.epam.reportportal.utils.files.ByteSource;
import com.epam.reportportal.utils.files.Utils;
import com.google.common.io.Resources;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
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
		ByteSource source = Utils.getFileAsByteSource(new File("files/file.css"));
		try (InputStream is = source.openStream()) {
			try (OutputStream os = Files.newOutputStream(file.toPath())) {
				Utils.copyStreams(is, os);
			}
		}
		ReportPortal.emitLog("LAUNCH LOG MESAGE WITH ATTACHMENT", "error", new Date(), file);
		LoggingUtils.log(Resources.asByteSource(Resources.getResource(JSON_FILE_PATH)).read(), "I'm logging content via BASE64");
	}

	@Test
	public void logJsonFile() throws IOException {
		/* here we are logging some binary data as file (useful for selenium) */
		File file = File.createTempFile("rp-test", ".json");
		ByteSource source = Utils.getFileAsByteSource(new File(JSON_FILE_PATH));
		try (InputStream is = source.openStream()) {
			try (OutputStream os = Files.newOutputStream(file.toPath())) {
				Utils.copyStreams(is, os);
			}
		}

		for (int i = 0; i < 1; i++) {
			LOGGER.info("RP_MESSAGE#FILE#{}#{}", file.getAbsolutePath(), "I'm logging content via temp file");
		}
	}

}
