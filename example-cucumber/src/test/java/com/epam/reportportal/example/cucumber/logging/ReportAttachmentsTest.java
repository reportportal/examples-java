package com.epam.reportportal.example.cucumber.logging;

import com.epam.reportportal.example.cucumber.util.LoggingUtils;
import com.epam.reportportal.example.cucumber.util.MagicRandomizer;
import com.epam.reportportal.service.ReportPortal;
import com.epam.reportportal.utils.files.Utils;
import cucumber.api.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

public class ReportAttachmentsTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportAttachmentsTest.class);
	public static final String XML_FILE_PATH = "files/file.xml";
	public static final String JSON_FILE_PATH = "files/file.json";

	@Given("I attach logCss")
	public void logCss() {
		LoggingUtils.log(new File("files/file.css"), "I'm logging CSS");
	}

	@Given("I attach logHtml")
	public void logHtml() {
		LoggingUtils.log(new File("files/file.html"), "I'm logging HTML");
	}

	@Given("I attach logPdf")
	public void logPdf() {
		LoggingUtils.log(new File("files/file.pdf"), "I'm logging PDF");
	}

	@Given("I attach logZip")
	public void logZip() {
		LoggingUtils.log(new File("files/file.zip"), "I'm logging ZIP");
	}

	@Given("I attach logHar")
	public void logHar() {
		LoggingUtils.log(new File("files/file.har"), "I'm logging HAR");
	}

	@Given("I attach logJavascript")
	public void logJavascript() {
		LoggingUtils.log(new File("files/file.js"), "I'm logging JS");
	}

	@Given("I attach logPhp")
	public void logPhp() {
		LoggingUtils.log(new File("files/file.php"), "I'm logging php");
	}

	@Given("I attach logPlain")
	public void logPlain() {
		LoggingUtils.log(new File("files/file.txt"), "I'm logging txt");
	}

	@Given("I attach logCsv")
	public void logCsv() {
		LoggingUtils.log(new File("files/file.csv"), "I'm logging txt");
	}

	@Given("I attach logCmd")
	public void logCmd() {
		LoggingUtils.log(new File("files/file.cmd"), "I'm logging txt");
	}

	@Given("I attach logXmlBase64")
	public void logXmlBase64() throws IOException {
		/* here we are logging some binary data as BASE64 string */
		LOGGER.info(
				"RP_MESSAGE#BASE64#{}#{}",
				Base64.getEncoder().encodeToString(Utils.getFileAsByteSource(new File(XML_FILE_PATH)).read()),
				"I'm logging content via BASE64"
		);
	}

	@Given("I attach logXmlFile")
	public void logXmlFile() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File(XML_FILE_PATH).getAbsolutePath(), "I'm logging content via temp file");
	}

	@Given("I attach logJsonBase64")
	public void logJsonBase64() throws IOException {
		/* here we are logging some binary data as BASE64 string */
		ReportPortal.emitLog("ITEM LOG MESSAGE", "error", new Date());
		ReportPortal.emitLog("ITEM LOG MESSAGE WITH ATTACHMENT", "error", new Date(), new File("files/css.css"));
		LOGGER.info(
				"RP_MESSAGE#BASE64#{}#{}",
				Base64.getEncoder().encodeToString(Utils.getFileAsByteSource(new File(JSON_FILE_PATH)).read()),
				"I'm logging content via BASE64"
		);
	}

	@Given("I attach logJsonFile")
	public void logJsonFile() {
		/* here we are logging some binary data as file (useful for selenium) */
		for (int i = 0; i < 1; i++) {
			LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File(JSON_FILE_PATH).getAbsolutePath(), "I'm logging content via temp file");
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
					Base64.getEncoder().encodeToString(Utils.getFileAsByteSource(new File(image)).read()),
					"Pug is " + (happy ? "HAPPY" : "NOT HAPPY")
			);
		}
	}

	private String getImageResource(boolean lucky) {
		return "pug/" + (lucky ? "lucky.jpg" : "unlucky.jpg");
	}
}
