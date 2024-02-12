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

import com.epam.reportportal.example.cucumber4.util.AttachmentHelper;
import com.epam.reportportal.example.cucumber4.util.LoggingUtils;
import com.epam.reportportal.example.cucumber4.util.MagicRandomizer;
import com.epam.reportportal.service.ReportPortal;
import com.epam.reportportal.utils.files.Utils;
import io.cucumber.java.en.Given;

import java.io.*;
import java.util.Date;

public class ReportAttachmentsTest {
	public static final String FILE_FOLDER_PATH = "src/test/resources/files";
	public static final String XML_FILE_PATH = FILE_FOLDER_PATH + "/file.xml";
	public static final String JSON_FILE_PATH = FILE_FOLDER_PATH + "/file.json";

	@Given("I attach logCss")
	public void logCss() {
		LoggingUtils.log(new File(FILE_FOLDER_PATH + "/file.css"), "I'm logging CSS");
	}

	@Given("I attach logHtml")
	public void logHtml() {
		LoggingUtils.log(new File(FILE_FOLDER_PATH + "/file.html"), "I'm logging HTML");
	}

	@Given("I attach logPdf")
	public void logPdf() {
		LoggingUtils.log(new File(FILE_FOLDER_PATH + "/file.pdf"), "I'm logging PDF");
	}

	@Given("I attach logZip")
	public void logZip() {
		LoggingUtils.log(new File(FILE_FOLDER_PATH + "/file.zip"), "I'm logging ZIP");
	}

	@Given("I attach logHar")
	public void logHar() {
		LoggingUtils.log(new File(FILE_FOLDER_PATH + "/file.har"), "I'm logging HAR");
	}

	@Given("I attach logJavascript")
	public void logJavascript() {
		LoggingUtils.log(new File(FILE_FOLDER_PATH + "/file.js"), "I'm logging JS");
	}

	@Given("I attach logPhp")
	public void logPhp() {
		LoggingUtils.log(new File(FILE_FOLDER_PATH + "/file.php"), "I'm logging PHP");
	}

	@Given("I attach logPlain")
	public void logPlain() {
		LoggingUtils.log(new File(FILE_FOLDER_PATH + "/file.txt"), "I'm logging TXT");
	}

	@Given("I attach logCsv")
	public void logCsv() {
		LoggingUtils.log(new File(FILE_FOLDER_PATH + "/file.csv"), "I'm logging CSV");
	}

	@Given("I attach logCmd")
	public void logCmd() {
		LoggingUtils.log(new File(FILE_FOLDER_PATH + "/file.cmd"), "I'm logging CMD");
	}

	@Given("I attach logXmlBase64")
	public void logXmlBase64() throws IOException {
		/* here we are logging some binary data as BASE64 string */
		LoggingUtils.log(Utils.getFileAsByteSource(new File(XML_FILE_PATH)).read(), "I'm logging content via BASE64");
	}

	@Given("I attach logXmlFile")
	public void logXmlFile() {
		File file = AttachmentHelper.getFileFromResources(FILE_FOLDER_PATH, "file", "xml");
		LoggingUtils.log(file, "I'm logging content via temp file");
	}

	@Given("I attach logBase64")
	public void logBase64() throws IOException {
		/* here we are logging some binary data as BASE64 string */
		ReportPortal.emitLog("ITEM LOG MESSAGE", "error", new Date());
		ReportPortal.emitLog("ITEM LOG MESSAGE WITH ATTACHMENT", "error", new Date(), new File(FILE_FOLDER_PATH + "/file.css"));
		LoggingUtils.log(Utils.getFileAsByteSource(new File(JSON_FILE_PATH)).read(), "I'm logging content via BASE64");
	}

	@Given("I attach logJsonFile")
	public void logJsonFile() {
		/* here we are logging some binary data as file (useful for selenium) */
		File file = AttachmentHelper.getFileFromResources(FILE_FOLDER_PATH, "file", "json");
		LoggingUtils.log(file, "I'm logging content via temp file");
	}

	@Given("I attach logImageBase64")
	public void logImageBase64() throws IOException {
		/* Generate 10 logs with pugs. Pug may be lucky or unlucky based on randomizer */
		for (int i = 0; i < 20; i++) {
			/* 50 percents. So we should have approximately same count of lucky and unlucky pugs */
			boolean happy = MagicRandomizer.checkYourLucky(30);
			String image = getImageResource(happy);
			LoggingUtils.log(Utils.getFileAsByteSource(new File(image)).read(), "Pug is " + (happy ? "HAPPY" : "NOT HAPPY"));
		}
	}

	private String getImageResource(boolean lucky) {
		return "src/test/resources/pug/" + (lucky ? "lucky.jpg" : "unlucky.jpg");
	}
}