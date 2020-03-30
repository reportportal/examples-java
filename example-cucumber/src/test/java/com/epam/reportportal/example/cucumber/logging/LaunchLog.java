package com.epam.reportportal.example.cucumber.logging;

import ch.qos.logback.classic.Level;
import com.epam.reportportal.example.cucumber.util.Attachment;
import com.epam.reportportal.service.ReportPortal;
import cucumber.api.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Random;

import static com.epam.reportportal.example.cucumber.util.AttachmentHelper.FILE_NAME;
import static com.epam.reportportal.example.cucumber.util.AttachmentHelper.getFileFromResources;

/**
 * @author <a href="mailto:ihar_kahadouski@epam.com">Ihar Kahadouski</a>
 */
public class LaunchLog {

	private static final Logger LOGGER = LoggerFactory.getLogger(LaunchLog.class);

	@Given("I attach files to launch logs")
	public void reportStep() {
		LOGGER.info("Add logs with attachments to launch");
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
