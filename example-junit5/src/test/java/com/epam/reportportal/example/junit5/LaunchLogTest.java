package com.epam.reportportal.example.junit5;

import ch.qos.logback.classic.Level;
import com.epam.reportportal.example.junit5.util.Attachment;
import com.epam.reportportal.service.ReportPortal;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import static com.epam.reportportal.example.junit5.util.AttachmentHelper.FILE_NAME;
import static com.epam.reportportal.example.junit5.util.AttachmentHelper.getFileFromResources;

public class LaunchLogTest {

	@BeforeAll
	static void beforeAll() throws IOException {
		ReportPortal.emitLaunchLog("LAUNCH LOG MESSAGE TRACE LEVEL", "trace", new Date());
		ReportPortal.emitLaunchLog("LAUNCH LOG MESSAGE DEBUG LEVEL", "debug", new Date());
		ReportPortal.emitLaunchLog("LAUNCH LOG MESSAGE INFO LEVEL", "info", new Date());
		ReportPortal.emitLaunchLog("LAUNCH LOG MESSAGE WARN LEVEL", "warn", new Date());
		ReportPortal.emitLaunchLog("LAUNCH LOG MESSAGE ERROR LEVEL", "error", new Date());
		ReportPortal.emitLaunchLog("LAUNCH LOG MESSAGE CUSTOM LEVEL", "66666", new Date());

		Arrays.stream(Attachment.values()).forEach(it -> launchLogWithAttachment(randomLogLevel(), FILE_NAME, it));
	}

	@Test
	void launchLogTest() {
		System.out.println("launch-log");
	}

	private static void launchLogWithAttachment(String level, String name, Attachment attachment) {
		String message = String.format("LAUNCH LOG MESSAGE WITH %s ATTACHMENT", attachment.name());
		ReportPortal.emitLaunchLog(message, level, new Date(), getFileFromResources(name, attachment.getExtension()));
	}

	private static String randomLogLevel() {
		return Level.fromLocationAwareLoggerInteger(new Random().nextInt(5) * 10).toString();
	}
}
