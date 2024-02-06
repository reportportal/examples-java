package com.epam.reportportal.example.testng.logback.logging;

import com.epam.reportportal.example.testng.logback.LoggingUtils;
import com.epam.reportportal.utils.files.Utils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * IMPROVED version of file logging example
 * Uses external utilities
 *
 * @author Andrei Varabyeu
 */
public class XmlLoggingBetterTest {

	public static final String XML_FILE_PATH = "xml/file.xml";

	@Test
	public void logXmlBase64_1() throws IOException {
		/* here we are logging some binary data as BASE64 string */
		LoggingUtils.log(Utils.getFile(new File(XML_FILE_PATH)).read(), "I'm logging content via BASE64");
	}

	@Test
	public void logXmlFile_1() throws IOException, InterruptedException {
		/* here we are logging some binary data as file (useful for selenium) */
		LoggingUtils.log(new File(XML_FILE_PATH), "I'm logging content via BASE64");
	}
}
