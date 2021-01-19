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

import ch.qos.logback.classic.Level;
import com.epam.reportportal.example.cucumber4.util.Attachment;
import com.epam.reportportal.service.ReportPortal;
import io.cucumber.java.en.Given;
import org.slf4j.Logger;

import java.util.Date;
import java.util.Random;

import static com.epam.reportportal.example.cucumber4.util.AttachmentHelper.FILE_NAME;
import static com.epam.reportportal.example.cucumber4.util.AttachmentHelper.getFileFromResources;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author <a href="mailto:ihar_kahadouski@epam.com">Ihar Kahadouski</a>
 */
public class LaunchLog {

	private static final Logger LOGGER = getLogger(LaunchLog.class);

	@Given("I attach files to launch logs")
	public void iAttachFilesToLaunchLogs() {
		LOGGER.info("I attach files to launch log");
		for (Attachment attachment : Attachment.values()) {
			launchLogWithAttachment(randomLogLevel(), FILE_NAME, attachment);
		}
	}

	private static void launchLogWithAttachment(String level, String name, Attachment attachment) {
		String message = String.format("LAUNCH LOG MESSAGE WITH %s ATTACHMENT", attachment.name());
		ReportPortal.emitLaunchLog(message, level, new Date(), getFileFromResources(name, attachment.getExtension()));
	}

	private static String randomLogLevel() {
		return Level.fromLocationAwareLoggerInteger(new Random().nextInt(5) * 10).toString();
	}
}
