package com.epam.reportportal.example.junit.logging;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class LoggingTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonLoggingTest.class);

	@Test
	public void logCss() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("src/test/resources/files/file.css").getAbsolutePath(), "I'm logging CSS");
	}

	@Test
	public void logHtml() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("src/test/resources/files/file.html").getAbsolutePath(), "I'm logging HTML");
	}

	@Test
	public void logPdf() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("src/test/resources/files/file.pdf").getAbsolutePath(), "I'm logging PDF");
	}

	@Test
	public void logZip() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("src/test/resources/files/file.zip").getAbsolutePath(), "I'm logging ZIP");
	}

	@Test
	public void logHar() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("src/test/resources/files/file.har").getAbsolutePath(), "I'm logging HAR");
	}

	@Test
	public void logJavascript() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("src/test/resources/files/file.js").getAbsolutePath(), "I'm logging JS");
	}

	@Test
	public void logPhp() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("src/test/resources/files/file.php").getAbsolutePath(), "I'm logging PHP");
	}

	@Test
	public void logPlain() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("src/test/resources/files/file.txt").getAbsolutePath(), "I'm logging TXT");
	}

	@Test
	public void logCsv() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("src/test/resources/files/file.csv").getAbsolutePath(), "I'm logging CSV");
	}

	@Test
	public void logCmd() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("src/test/resources/files/file.cmd").getAbsolutePath(), "I'm logging CMD");
	}
}
