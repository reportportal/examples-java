package com.epam.reportportal.example.testng.log4j.logging;

import com.epam.reportportal.example.testng.log4j.LoggingUtils;
import com.epam.reportportal.message.ReportPortalMessage;
import com.epam.reportportal.utils.files.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ObjectMessage;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * JUST an example of file logging
 *
 * @author Andrei Varabyeu
 */
public class JsonLoggingTest {

	private static final Logger LOGGER = LogManager.getLogger(JsonLoggingTest.class);
	public static final String JSON_FILE_PATH = "xml/file.json";

	@Test
	public void logJsonBase64() throws IOException {
		/* here we are logging some binary data as BASE64 string */
		LoggingUtils.log(Utils.getFileAsByteSource(new File(JSON_FILE_PATH)).read(), "I'm logging content via BASE64");
	}

	@Test
	public void logJsonFile() throws IOException, InterruptedException {
		/* here we are logging some binary data as file (useful for selenium) */
		File file = File.createTempFile("rp-test", ".json");
		try (InputStream is = Files.newInputStream(Path.of(JSON_FILE_PATH))) {
			try (OutputStream os = Files.newOutputStream(file.toPath())){
				Utils.copyStreams(is, os);
			}
		}

		for (int i = 0; i < 1; i++) {
			LoggingUtils.log(file, "I'm logging content via temp file");
		}
		Thread.sleep(5000L);

	}

	@Test
	public void logRepMessage() throws IOException {
		/* here we are logging some binary data as file (useful for selenium) */
		File file = File.createTempFile("rp-test", ".json");
		LOGGER.info(new ObjectMessage(new ReportPortalMessage(file, "File message")));
	}

}
