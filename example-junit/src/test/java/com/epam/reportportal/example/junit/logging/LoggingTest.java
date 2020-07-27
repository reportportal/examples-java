package com.epam.reportportal.example.junit.logging;

import com.epam.reportportal.example.junit.LoggingUtils;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by avarabyeu on 3/9/17.
 */
public class LoggingTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonLoggingTest.class);

	@Test
	public void logCss() throws IOException {
		File file = File.createTempFile("rp-test", ".css");
		Resources.asByteSource(Resources.getResource("files/file.css")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging CSS");
	}

	@Test
	public void logHtml() throws IOException {
		File file = File.createTempFile("rp-test", ".html");
		Resources.asByteSource(Resources.getResource("files/file.html")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging HTML");
	}

	@Test
	public void logPdf() throws IOException {
		File file = File.createTempFile("rp-test", ".pdf");
		Resources.asByteSource(Resources.getResource("files/file.pdf")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging PDF");
	}

	@Test
	public void logZip() throws IOException {
		File file = File.createTempFile("rp-test", ".zip");
		Resources.asByteSource(Resources.getResource("files/file.zip")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging ZIP");
	}

	//    @Test
	//    public void logHar() throws IOException {
	//        File file = File.createTempFile("har", ".json");
	//        Resources.asByteSource(Resources.getResource("files/har.har")).copyTo(Files.asByteSink(file));
	//        LoggingUtils.log(file, "I'm logging HAR");
	//    }

	@Test
	public void logJavascript() throws IOException {
		File file = File.createTempFile("rp-test", ".js");
		Resources.asByteSource(Resources.getResource("files/file.js")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging JS");
	}

	@Test
	public void logPhp() throws IOException {
		File file = File.createTempFile("rp-test", ".php");
		Resources.asByteSource(Resources.getResource("files/file.php")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging php");
	}

	@Test
	public void logPlain() throws IOException {
		File file = File.createTempFile("rp-test", ".txt");
		Resources.asByteSource(Resources.getResource("files/file.txt")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging txt");
	}

	@Test
	public void logCsv() throws IOException {
		File file = File.createTempFile("rp-test", ".csv");
		Resources.asByteSource(Resources.getResource("files/file.csv")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging txt");
	}

	@Test
	public void logCmd() throws IOException {
		File file = File.createTempFile("rp-test", ".cmd");
		Resources.asByteSource(Resources.getResource("files/file.cmd")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging txt");
	}
}
