package com.epam.reportportal.example.junit.logging;

import com.epam.reportportal.example.junit.LoggingUtils;
import com.epam.reportportal.utils.files.ByteSource;
import com.epam.reportportal.utils.files.Utils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * IMPROVED version of file logging example
 * Uses external utilities
 *
 * @author Andrei Varabyeu
 */
public class XmlLoggingBetterTest {

	public static final String XML_FILE_PATH = "xml/file.xml";

	@Test
	public void logXmlBase64() throws IOException {
		/* here we are logging some binary data as BASE64 string */
		LoggingUtils.log(Utils.getFile(new File(XML_FILE_PATH)).read(), "I'm logging content via BASE64");
	}

	@Test
	public void logXmlFile() throws IOException {
		/* here we are logging some binary data as file (useful for selenium) */
		File file = File.createTempFile("rp-test", "xml");
		ByteSource source = Utils.getFileAsByteSource(new File(XML_FILE_PATH));
		try (InputStream is = source.openStream()) {
			try (OutputStream os = java.nio.file.Files.newOutputStream(file.toPath())) {
				Utils.copyStreams(is, os);
			}
		}
		LoggingUtils.log(file, "I'm logging content via BASE64");
	}
}
