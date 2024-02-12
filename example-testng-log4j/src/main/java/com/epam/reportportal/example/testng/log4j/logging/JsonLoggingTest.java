package com.epam.reportportal.example.testng.log4j.logging;

import com.epam.reportportal.example.testng.log4j.util.AttachmentHelper;
import com.epam.reportportal.example.testng.log4j.util.LoggingUtils;
import com.epam.reportportal.message.ReportPortalMessage;
import com.epam.reportportal.utils.files.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ObjectMessage;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * JUST an example of file logging
 *
 * @author Andrei Varabyeu
 */
public class JsonLoggingTest {

	private static final Logger LOGGER = LogManager.getLogger(JsonLoggingTest.class);
	public static final String FILE_FOLDER_PATH = "src/main/resources/xml";
	public static final String JSON_FILE_PATH = FILE_FOLDER_PATH + "/file.json";

	@Test
	public void logJsonBase64() throws IOException {
		/* here we are logging some binary data as BASE64 string */
		LoggingUtils.log(Utils.getFileAsByteSource(new File(JSON_FILE_PATH)).read(), "I'm logging content via BASE64");
	}

	@Test
	public void logJsonFile() {
		/* here we are logging some binary data as file (useful for selenium) */
		File file = AttachmentHelper.getFileFromResources(FILE_FOLDER_PATH, "file", "json");
		for (int i = 0; i < 1; i++) {
			LoggingUtils.log(file, "I'm logging content via temp file");
		}
	}

	@Test
	public void logRepMessage() throws IOException {
		/* here we are logging some binary data as file (useful for selenium) */
		File file = AttachmentHelper.getFileFromResources(FILE_FOLDER_PATH, "file", "json");
		LOGGER.info(new ObjectMessage(new ReportPortalMessage(file, "File message")));
	}

}
