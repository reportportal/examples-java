package com.epam.reportportal.example.junit.logging;

import com.epam.reportportal.example.junit.util.LoggingUtils;
import org.junit.Test;

import java.io.File;

public class LoggingTest {
	public static final String FILE_FOLDER_PATH = "src/test/resources/files";

	@Test
	public void logCss() {
		LoggingUtils.log(new File(FILE_FOLDER_PATH + "/file.css"), "I'm logging CSS");
	}

	@Test
	public void logHtml() {
		LoggingUtils.log(new File(FILE_FOLDER_PATH + "/file.html"), "I'm logging HTML");
	}

	@Test
	public void logPdf() {
		LoggingUtils.log(new File(FILE_FOLDER_PATH + "/file.pdf"), "I'm logging PDF");
	}

	@Test
	public void logZip() {
		LoggingUtils.log(new File(FILE_FOLDER_PATH + "/file.zip"), "I'm logging ZIP");
	}

	@Test
	public void logHar() {
		LoggingUtils.log(new File(FILE_FOLDER_PATH + "/file.har"), "I'm logging HAR");
	}

	@Test
	public void logJavascript() {
		LoggingUtils.log(new File(FILE_FOLDER_PATH + "/file.js"), "I'm logging JS");
	}

	@Test
	public void logPhp() {
		LoggingUtils.log(new File(FILE_FOLDER_PATH + "/file.php"), "I'm logging PHP");
	}

	@Test
	public void logPlain() {
		LoggingUtils.log(new File(FILE_FOLDER_PATH + "/file.txt"), "I'm logging TXT");
	}

	@Test
	public void logCsv() {
		LoggingUtils.log(new File(FILE_FOLDER_PATH + "/file.csv"), "I'm logging CSV");
	}

	@Test
	public void logCmd() {
		LoggingUtils.log(new File(FILE_FOLDER_PATH + "/file.cmd"), "I'm logging CMD");
	}
}
