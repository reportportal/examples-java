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

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

public class ReportAttachmentsTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportAttachmentsTest.class);
	public static final String XML_FILE_PATH = "xml/file.xml";
	public static final String JSON_FILE_PATH = "xml/file.json";

	@Given("I attach logCss")
	public void logCss() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("files/file.css").getAbsolutePath(), "I'm logging CSS");
	}

	@Given("I attach logHtml")
	public void logHtml() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("files/file.html").getAbsolutePath(), "I'm logging CSS");
	}

	@Given("I attach logPdf")
	public void logPdf() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("files/file.pdf").getAbsolutePath(), "I'm logging CSS");
	}

	@Given("I attach logZip")
	public void logZip() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("files/file.zip").getAbsolutePath(), "I'm logging CSS");
	}

	@Given("I attach logHar")
	public void logHar() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("files/file.har").getAbsolutePath(), "I'm logging CSS");
	}

	@Given("I attach logJavascript")
	public void logJavascript() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("files/file.js").getAbsolutePath(), "I'm logging CSS");
	}

	@Given("I attach logPhp")
	public void logPhp() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("files/file.php").getAbsolutePath(), "I'm logging CSS");
	}

	@Given("I attach logPlain")
	public void logPlain() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("files/file.txt").getAbsolutePath(), "I'm logging CSS");
	}

	@Given("I attach logCsv")
	public void logCsv() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("files/file.csv").getAbsolutePath(), "I'm logging CSS");
	}

	@Given("I attach logCmd")
	public void logCmd() {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", new File("files/file.cmd").getAbsolutePath(), "I'm logging CSS");
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
