package com.epam.reportportal.example.testng.logback.feature;

import com.epam.reportportal.example.testng.logback.LoggingUtils;
import com.epam.reportportal.service.ReportPortal;
import com.epam.reportportal.testng.BaseTestNGListener;
import com.epam.reportportal.testng.TestNGService;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Listeners({ TestLaunchLevelLogs.ExtendedListener.class })
public class TestLaunchLevelLogs {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExtendedListener.class);

	@Test
	public void testLaunchLogs() throws IOException {
		LOGGER.info("A test step log entry.");
		ReportPortal.emitLaunchLog("A manual launch log using 'ReportPortal.emitLaunchLog'.", "INFO", new Date());
		File file = File.createTempFile("rp-test", ".css");
		Resources.asByteSource(Resources.getResource("files/css.css")).copyTo(Files.asByteSink(file));
		ReportPortal.emitLaunchLog("A manual launch log with attachment using 'ReportPortal.emitLaunchLog'.", "INFO", new Date(), file);
	}

	public static final class ExtendedListener extends BaseTestNGListener {
		private static final Logger LOGGER = LoggerFactory.getLogger(ExtendedListener.class);

		public ExtendedListener() {
			super(new TestNGService());
		}

		@Override
		public void onExecutionStart() {
			super.onExecutionStart();
			LOGGER.info("These logs should appear on launch level.");
			try {
				File file = File.createTempFile("rp-test", ".css");
				Resources.asByteSource(Resources.getResource("files/css.css")).copyTo(Files.asByteSink(file));
				LoggingUtils.log(file, "They should also support attachments.");
			} catch (IOException e) {
				LOGGER.error("Error on logging with attachments", e);
			}
		}
	}
}
