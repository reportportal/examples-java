package com.epam.reportportal.example.junit.logging;

import com.epam.reportportal.example.junit.util.AttachmentHelper;
import com.epam.reportportal.utils.files.Utils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

/**
 * JUST an example of file logging
 *
 * @author Andrei Varabyeu
 */
public class XmlLoggingTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(XmlLoggingTest.class);
	public static final String XML_FILE_FOLDER_PATH = "src/test/resources/xml";
	public static final String XML_FILE_PATH = XML_FILE_FOLDER_PATH + "/file.xml";

	@Test
	public void logXmlBase64() throws IOException {
		/* here we are logging some binary data as BASE64 string */
		LOGGER.info(
				"RP_MESSAGE#BASE64#{}#{}",
				Base64.getEncoder().encodeToString(Utils.getFileAsByteSource(new File(XML_FILE_PATH)).read()),
				"I'm logging content via BASE64"
		);
	}

	@Test
	public void logXmlFile() {
		/* here we are logging some binary data as file (useful for selenium) */
		File file = AttachmentHelper.getFileFromResources(XML_FILE_FOLDER_PATH, "file", "xml");
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", file.getAbsolutePath(), "I'm logging content via temp file");
	}
}
