package com.epam.reportportal.example.cucumber3.logging;

import ch.qos.logback.classic.Level;
import com.epam.reportportal.example.cucumber3.util.Attachment;
import com.epam.reportportal.service.ReportPortal;
import cucumber.api.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import static com.epam.reportportal.example.cucumber3.util.AttachmentHelper.FILE_NAME;
import static com.epam.reportportal.example.cucumber3.util.AttachmentHelper.getFileFromResources;

/**
 * @author <a href="mailto:ihar_kahadouski@epam.com">Ihar Kahadouski</a>
 */
public class LaunchLog {

	private static final Logger LOGGER = LoggerFactory.getLogger(LaunchLog.class);

	@Given("I attach files to launch logs")
	public void iAttachFilesToLaunchLogs() {
		Arrays.stream(Attachment.values()).forEach(it -> launchLogWithAttachment(randomLogLevel(), FILE_NAME, it));
		LOGGER.info("I attach files to launch log");
	}

	private static void launchLogWithAttachment(String level, String name, Attachment attachment) {
		String message = String.format("LAUNCH LOG MESSAGE WITH %s ATTACHMENT", attachment.name());
		ReportPortal.emitLaunchLog(message, level, new Date(), getFileFromResources(name, attachment.getExtension()));
	}

	private static String randomLogLevel() {
		return Level.fromLocationAwareLoggerInteger(new Random().nextInt(5) * 10).toString();
	}
}