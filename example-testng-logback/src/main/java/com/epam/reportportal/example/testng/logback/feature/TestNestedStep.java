package com.epam.reportportal.example.testng.logback.feature;

import com.epam.reportportal.annotations.Step;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ ReportPortalTestNGListener.class })
public class TestNestedStep {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestNestedStep.class);

	@Step(description = "A nested step description")
	public void nestedStep() {
		LOGGER.info("A nested step log entry.");
	}

	@Test
	public void testNestedStep() {
		LOGGER.info("A test step log entry.");
		nestedStep();
	}
}
