package com.epam.reportportal.example.testng.logback.logging;

import com.epam.reportportal.example.testng.logback.LoggingUtils;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.testng.annotations.BeforeMethod;
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
	public void logXmlBase64_1() throws IOException, InterruptedException {
		if(new File("/Users/yumfriez/qwe/rp.lock").exists()) {
			new File("/Users/yumfriez/qwe/xml_ex.txt").createNewFile();
		}
		/* here we are logging some binary data as BASE64 string */
		LoggingUtils.log(Resources.asByteSource(Resources.getResource(XML_FILE_PATH)).read(), "I'm logging content via BASE64");
	}

	@Test
	public void logXmlFile_1() throws IOException, InterruptedException {
		/* here we are logging some binary data as file (useful for selenium) */
		File file = File.createTempFile("rp-test", "xml");
		Resources.asByteSource(Resources.getResource(XML_FILE_PATH)).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging content via BASE64");
	}
}
