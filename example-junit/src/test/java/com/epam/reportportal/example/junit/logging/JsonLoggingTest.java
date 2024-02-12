package com.epam.reportportal.example.junit.logging;

import com.epam.reportportal.example.junit.util.AttachmentHelper;
import com.epam.reportportal.example.junit.util.LoggingUtils;
import com.epam.reportportal.service.ReportPortal;
import com.epam.reportportal.utils.files.Utils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * JUST an example of file logging
 *
 * @author Andrei Varabyeu
 */
public class JsonLoggingTest {
	public static final String JSON_FILE_PATH = "src/test/resources/xml";

	@Test
	public void logJsonBase64() throws IOException {
		/* here we are logging some binary data as BASE64 string */
		ReportPortal.emitLog("LAUNCH LOG MESSAGE", "error", new Date());

		File file = AttachmentHelper.getFileFromResources(JSON_FILE_PATH, "file", "json");
		ReportPortal.emitLog("LAUNCH LOG MESSAGE WITH ATTACHMENT", "error", new Date(), file);
		LoggingUtils.log(file, "I'm logging content via temp file");
		LoggingUtils.log(Utils.getFileAsByteSource(file).read(), "I'm logging content via BASE64");
	}

	@Test
	public void logJsonFile() {
		/* here we are logging some binary data as file (useful for selenium) */
		File file = AttachmentHelper.getFileFromResources(JSON_FILE_PATH, "file", "json");
		for (int i = 0; i < 1; i++) {
			LoggingUtils.log(file, "I'm logging content via temp file");
		}
	}

}
