package com.epam.reportportal.example.junit5;

import com.epam.reportportal.service.ReportPortal;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;

import static com.epam.reportportal.example.junit5.Util.getFileFromResources;

public class LaunchLogTest {

	private static final String FILE_NAME = "file";

	@BeforeAll
	static void beforeAll() throws IOException {
		ReportPortal.emitLaunchLog("LAUNCH LOG MESSAGE TRACE LEVEL", "trace", new Date());
		ReportPortal.emitLaunchLog("LAUNCH LOG MESSAGE DEBUG LEVEL", "debug", new Date());
		ReportPortal.emitLaunchLog("LAUNCH LOG MESSAGE INFO LEVEL", "info", new Date());
		ReportPortal.emitLaunchLog("LAUNCH LOG MESSAGE WARN LEVEL", "warn", new Date());
		ReportPortal.emitLaunchLog("LAUNCH LOG MESSAGE ERROR LEVEL", "error", new Date());
		ReportPortal.emitLaunchLog("LAUNCH LOG MESSAGE CUSTOM LEVEL", "66666", new Date());

		launchLogWithAttachment("info", FILE_NAME, "css");
		launchLogWithAttachment("debug", FILE_NAME, "xml");
		launchLogWithAttachment("trace", FILE_NAME, "js");
		launchLogWithAttachment("warn", FILE_NAME, "json");
		launchLogWithAttachment("error", FILE_NAME, "php");
		launchLogWithAttachment("info", FILE_NAME, "zip");
		launchLogWithAttachment("trace", FILE_NAME, "tar.gz");
		launchLogWithAttachment("trace", FILE_NAME, "tar");
		launchLogWithAttachment("error", FILE_NAME, "txt");
		launchLogWithAttachment("info", FILE_NAME, "html");
		launchLogWithAttachment("debug", FILE_NAME, "jpg");
		launchLogWithAttachment("debug", FILE_NAME, "png");
		launchLogWithAttachment("debug", FILE_NAME, "cmd");
		launchLogWithAttachment("warn", FILE_NAME, "csv");
		launchLogWithAttachment("warn", FILE_NAME, "pdf");
		launchLogWithAttachment("warn", FILE_NAME, "har");
	}

	@Test
	void launchLogTest() {
		System.out.println("launch-log");
	}

	private static void launchLogWithAttachment(String level, String name, String extension) throws IOException {
		String message = String.format("LAUNCH LOG MESSAGE WITH %s ATTACHMENT", extension.toUpperCase());
		ReportPortal.emitLaunchLog(message, level, new Date(), getFileFromResources(name, extension));
	}
}
