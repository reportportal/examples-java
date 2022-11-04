package com.epam.reportportal.example.jbehave.steps;

import com.epam.reportportal.example.jbehave.LoggingUtils;
import com.epam.reportportal.example.jbehave.MagicRandomizer;
import com.epam.reportportal.service.ReportPortal;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.jbehave.core.annotations.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

public class ReportAttachmentsTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportAttachmentsTest.class);
	public static final String XML_FILE_PATH = "xml/file.xml";
	public static final String JSON_FILE_PATH = "xml/file.json";

	@Given("I attach logCss")
	public void logCss() throws IOException {
		File file = File.createTempFile("rp-test", ".css");
		Resources.asByteSource(Resources.getResource("files/css.css")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging CSS");
	}

	@Given("I attach logHtml")
	public void logHtml() throws IOException {
		File file = File.createTempFile("rp-test", ".html");
		Resources.asByteSource(Resources.getResource("files/html.html")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging HTML");
	}

	@Given("I attach logPdf")
	public void logPdf() throws IOException {
		File file = File.createTempFile("rp-test", ".pdf");
		Resources.asByteSource(Resources.getResource("files/test.pdf")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging PDF");
	}

	@Given("I attach logZip")
	public void logZip() throws IOException {
		File file = File.createTempFile("rp-test", ".zip");
		Resources.asByteSource(Resources.getResource("files/demo.zip")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging ZIP");
	}

	@Given("I attach logHar")
	public void logHar() throws IOException {
		File file = File.createTempFile("har", ".json");
		Resources.asByteSource(Resources.getResource("files/har.har")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging HAR");
	}

	@Given("I attach logJavascript")
	public void logJavascript() throws IOException {
		File file = File.createTempFile("rp-test", ".js");
		Resources.asByteSource(Resources.getResource("files/javascript.js")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging JS");
	}

	@Given("I attach logPhp")
	public void logPhp() throws IOException {
		File file = File.createTempFile("rp-test", ".php");
		Resources.asByteSource(Resources.getResource("files/php.php")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging php");
	}

	@Given("I attach logPlain")
	public void logPlain() throws IOException {
		File file = File.createTempFile("rp-test", ".txt");
		Resources.asByteSource(Resources.getResource("files/plain.txt")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging txt");
	}

	@Given("I attach logCsv")
	public void logCsv() throws IOException {
		File file = File.createTempFile("rp-test", ".csv");
		Resources.asByteSource(Resources.getResource("files/Test.csv")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging txt");
	}

	@Given("I attach logCmd")
	public void logCmd() throws IOException {
		File file = File.createTempFile("rp-test", ".cmd");
		Resources.asByteSource(Resources.getResource("files/Test.cmd")).copyTo(Files.asByteSink(file));
		LoggingUtils.log(file, "I'm logging txt");
	}

	@Given("I attach logXmlBase64")
	public void logXmlBase64() throws IOException {
		/* here we are logging some binary data as BASE64 string */
		LOGGER.info(
				"RP_MESSAGE#BASE64#{}#{}",
				Base64.getEncoder().encodeToString(Resources.asByteSource(Resources.getResource(XML_FILE_PATH)).read()),
				"I'm logging content via BASE64"
		);
	}

	@Given("I attach logXmlFile")
	public void logXmlFile() throws IOException {
		File file = File.createTempFile("rp-test", "xml");
		Resources.asByteSource(Resources.getResource(XML_FILE_PATH)).copyTo(Files.asByteSink(file));

		LOGGER.info("RP_MESSAGE#FILE#{}#{}", file.getAbsolutePath(), "I'm logging content via temp file");
	}

	@Given("I attach logJsonBase64")
	public void logJsonBase64() throws IOException {
		/* here we are logging some binary data as BASE64 string */
		ReportPortal.emitLog("ITEM LOG MESSAGE", "error", new Date());

		File file = File.createTempFile("rp-test", ".css");
		Resources.asByteSource(Resources.getResource("files/css.css")).copyTo(Files.asByteSink(file));
		ReportPortal.emitLog("ITEM LOG MESSAGE WITH ATTACHMENT", "error", new Date(), file);

		LOGGER.info(
				"RP_MESSAGE#BASE64#{}#{}",
				Base64.getEncoder().encodeToString(Resources.asByteSource(Resources.getResource(JSON_FILE_PATH)).read()),
				"I'm logging content via BASE64"
		);
	}

	@Given("I attach logJsonFile")
	public void logJsonFile() throws IOException, InterruptedException {
		/* here we are logging some binary data as file (useful for selenium) */
		File file = File.createTempFile("rp-test", ".json");
		Resources.asByteSource(Resources.getResource(JSON_FILE_PATH)).copyTo(Files.asByteSink(file));

		for (int i = 0; i < 1; i++) {
			LOGGER.info("RP_MESSAGE#FILE#{}#{}", file.getAbsolutePath(), "I'm logging content via temp file");
		}
	}

	@Given("I attach logImageBase64")
	public void logImageBase64() throws IOException {
		/* Generate 10 logs with pugs. Pug may be lucky or unlucky based on randomizer */
		for (int i = 0; i < 20; i++) {
			/* 50 percents. So we should have approximately same count of lucky and unlucky pugs */
			boolean happy = MagicRandomizer.checkYourLucky(30);
			String image = getImageResource(happy);

			LOGGER.info(
					"RP_MESSAGE#BASE64#{}#{}",
					Base64.getEncoder().encodeToString(Resources.asByteSource(Resources.getResource(image)).read()),
					"Pug is " + (happy ? "HAPPY" : "NOT HAPPY")
			);
		}
	}

	private String getImageResource(boolean lucky) {
		return "pug/" + (lucky ? "lucky.jpg" : "unlucky.jpg");
	}
}
