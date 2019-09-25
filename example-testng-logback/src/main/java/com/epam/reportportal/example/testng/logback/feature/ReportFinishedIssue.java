package com.epam.reportportal.example.testng.logback.feature;

import com.epam.reportportal.testng.BaseTestNGListener;
import com.epam.reportportal.testng.TestNGService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ ReportFinishedIssue.ExtendedListener.class })
public class ReportFinishedIssue {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportFinishedIssue.class);

	@Test
	public void testForceFinishIssue() {
		LOGGER.info("A test step log entry.");
	}

	public static final class ExtendedListener extends BaseTestNGListener {
		public ExtendedListener() {
			super(new TestNGService());
		}

		@Override
		public void onTestSuccess(ITestResult testResult) {
			// Do nothing, just skip this to verify force-finish by launch
		}
	}
}
