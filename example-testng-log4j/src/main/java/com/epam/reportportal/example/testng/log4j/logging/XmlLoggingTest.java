package com.epam.reportportal.example.testng.log4j.logging;

import com.epam.reportportal.example.testng.log4j.util.LoggingUtils;
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
	public static final String XML_FILE_PATH = "xml/file.xml";

	@Test
	public void logXmlBase64() throws IOException {
		/* here we are logging some binary data as BASE64 string */
		LoggingUtils.log(Utils.getFileAsByteSource(new File(XML_FILE_PATH)).read(), "I'm logging content via BASE64");
	}

	@Test
	public void logXmlFile() {
		/* here we are logging some binary data as file (useful for selenium) */
		LoggingUtils.log(new File(XML_FILE_PATH), "I'm logging content via temp file");
	}
}
