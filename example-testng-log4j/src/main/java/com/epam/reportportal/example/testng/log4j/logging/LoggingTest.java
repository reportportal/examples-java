package com.epam.reportportal.example.testng.log4j.logging;

import com.epam.reportportal.example.testng.log4j.LoggingUtils;
import org.testng.annotations.Test;
import com.google.common.io.Files;
import com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;

/**
 * Created by avarabyeu on 3/9/17.
 */
public class LoggingTest {

	@Test
	public void logCss() throws IOException {
		File file = File.createTempFile("rp-test", ".css");
		Resources.asByteSource(Resources.getResource("files/css.css")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging CSS");
	}

	@Test
	public void logHtml() throws IOException {
		File file = File.createTempFile("rp-test", ".html");
		Resources.asByteSource(Resources.getResource("files/html.html")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging HTML");
	}

	@Test
	public void logJavascript() throws IOException {
		File file = File.createTempFile("rp-test", ".js");
		Resources.asByteSource(Resources.getResource("files/javascript.js")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging JS");
	}

	@Test
	public void logPhp() throws IOException {
		File file = File.createTempFile("rp-test", ".php");
		Resources.asByteSource(Resources.getResource("files/php.php")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging php");
	}

	@Test
	public void logPlain() throws IOException {
		File file = File.createTempFile("rp-test", ".txt");
		Resources.asByteSource(Resources.getResource("files/plain.txt")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging txt");
	}

	@Test
	public void logCsv() throws IOException {
		File file = File.createTempFile("rp-test", ".csv");
		Resources.asByteSource(Resources.getResource("files/Test.csv")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging txt");
	}

	@Test
	public void logCmd() throws IOException {
		File file = File.createTempFile("rp-test", ".cmd");
		Resources.asByteSource(Resources.getResource("files/Test.cmd")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging txt");
	}
}
