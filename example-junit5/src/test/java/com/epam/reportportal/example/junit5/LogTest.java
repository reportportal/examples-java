package com.epam.reportportal.example.junit5;

import com.epam.reportportal.example.junit5.util.Attachment;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static com.epam.reportportal.example.junit5.util.Util.FILE_NAME;
import static com.epam.reportportal.example.junit5.util.Util.getFileFromResources;

public class LogTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogTest.class);

	@Test
	void LogTest() {
		LOGGER.trace("Trace level log");
		LOGGER.debug("Debug level log");
		LOGGER.info("Info level log");
		LOGGER.warn("Warn level log");
		LOGGER.error("Error level log");
		throw new IllegalArgumentException("Critical exception");
	}

	@Test
	void logWithAttachmentTest() {
		Arrays.stream(Attachment.values()).forEach(it -> {
			LOGGER.info(
					"RP_MESSAGE#FILE#{}#{}",
					getFileFromResources(FILE_NAME, it.getExtension()).getAbsolutePath(),
					String.format("I'm logging content via temp %s file", it.name())
			);
		});
	}
}
