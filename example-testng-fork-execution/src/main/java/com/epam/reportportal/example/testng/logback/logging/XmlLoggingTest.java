package com.epam.reportportal.example.testng.logback.logging;

import com.epam.reportportal.example.testng.logback.util.AttachmentHelper;
import com.epam.reportportal.example.testng.logback.util.LoggingUtils;
import com.epam.reportportal.utils.files.Utils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * JUST an example of file logging
 *
 * @author Andrei Varabyeu
 */
public class XmlLoggingTest {
	public static final String XML_FILE_FOLDER_PATH = "src/main/resources/xml";
	public static final String XML_FILE_PATH = XML_FILE_FOLDER_PATH + "/file.xml";

	@Test
	public void logXmlBase64() throws IOException {
		/* here we are logging some binary data as BASE64 string */
		LoggingUtils.log(Utils.getFileAsByteSource(new File(XML_FILE_PATH)).read(), "I'm logging content via BASE64");
	}

	@Test
	public void logXmlFile() {
		/* here we are logging some binary data as file (useful for selenium) */
		File file = AttachmentHelper.getFileFromResources(XML_FILE_FOLDER_PATH, "file", "xml");
		LoggingUtils.log(file, "I'm logging content via BASE64");
	}
}
