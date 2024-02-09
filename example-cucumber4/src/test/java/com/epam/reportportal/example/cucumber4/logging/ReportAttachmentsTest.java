/*
 * Copyright 2021 EPAM Systems
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.epam.reportportal.example.cucumber4.logging;

import com.epam.reportportal.example.cucumber4.util.MagicRandomizer;
import com.epam.reportportal.service.ReportPortal;

import com.epam.reportportal.utils.files.Utils;
import io.cucumber.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Base64;
import java.util.Date;

public class ReportAttachmentsTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportAttachmentsTest.class);
	public static final String XML_FILE_PATH = "src/test/resources/files/file.xml";
	public static final String JSON_FILE_PATH = "src/test/resources/files/file.json";

	@Given("I attach logCss")
	public void logCss() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("src/test/resources/files/file.css").getAbsolutePath(), "I'm logging CSS");
	}

	@Given("I attach logHtml")
	public void logHtml() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("src/test/resources/files/file.html").getAbsolutePath(), "I'm logging HTML");
	}

	@Given("I attach logPdf")
	public void logPdf() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("src/test/resources/files/file.pdf").getAbsolutePath(), "I'm logging PDF");
	}

	@Given("I attach logZip")
	public void logZip() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("src/test/resources/files/file.zip").getAbsolutePath(), "I'm logging ZIP");
	}

	@Given("I attach logHar")
	public void logHar() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("src/test/resources/files/file.har").getAbsolutePath(), "I'm logging HAR");
	}

	@Given("I attach logJavascript")
	public void logJavascript() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("src/test/resources/files/file.js").getAbsolutePath(), "I'm logging JS");
	}

	@Given("I attach logPhp")
	public void logPhp() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("src/test/resources/files/file.php").getAbsolutePath(), "I'm logging PHP");
	}

	@Given("I attach logPlain")
	public void logPlain() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("src/test/resources/files/file.txt").getAbsolutePath(), "I'm logging TXT");
	}

	@Given("I attach logCsv")
	public void logCsv() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("src/test/resources/files/file.csv").getAbsolutePath(), "I'm logging CSV");
	}

	@Given("I attach logCmd")
	public void logCmd() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("src/test/resources/files/file.cmd").getAbsolutePath(), "I'm logging CMD");
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

	@SuppressWarnings("IOStreamConstructor")
	@Given("I attach logXmlFile")
	public void logXmlFile() throws IOException {
		File file = File.createTempFile("rp-test", "xml");
		try (InputStream is = new FileInputStream(XML_FILE_PATH)) {
			try (OutputStream os = new FileOutputStream(file)) {
				Utils.copyStreams(is, os);
			}
		}
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", file.getAbsolutePath(), "I'm logging content via temp file");
	}

	@Given("I attach logBase64")
	public void logBase64() throws IOException {
		/* here we are logging some binary data as BASE64 string */
		ReportPortal.emitLog("ITEM LOG MESSAGE", "error", new Date());
		ReportPortal.emitLog("ITEM LOG MESSAGE WITH ATTACHMENT", "error", new Date(), new File("src/test/resources/files/file.css"));
		LOGGER.info(
				"RP_MESSAGE#BASE64#{}#{}",
				Base64.getEncoder().encodeToString(Utils.getFileAsByteSource(new File(JSON_FILE_PATH)).read()),
				"I'm logging content via BASE64"
		);
	}

	@SuppressWarnings("IOStreamConstructor")
	@Given("I attach logJsonFile")
	public void logJsonFile() throws IOException {
		/* here we are logging some binary data as file (useful for selenium) */
		File file = File.createTempFile("rp-test", ".json");
		try (InputStream is = new FileInputStream(JSON_FILE_PATH)) {
			try (OutputStream os = new FileOutputStream(file)) {
				Utils.copyStreams(is, os);
			}
		}

		LOGGER.info("RP_MESSAGE#FILE#{}#{}", file.getAbsolutePath(), "I'm logging content via temp file");
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
		return "src/test/resources/pug/" + (lucky ? "lucky.jpg" : "unlucky.jpg");
	}
}