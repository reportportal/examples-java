package com.epam.reportportal.example.testng.logback.step;

import com.epam.reportportal.annotations.Step;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:ivan_budayeu@epam.com">Ivan Budayeu</a>
 */
@Listeners(ReportPortalTestNGListener.class)
public class NestedStepsTest {

	public static final Logger LOGGER = LoggerFactory.getLogger(NestedStepsTest.class);

	@Test
	void testWithNestedSteps() {
		LOGGER.info("Main log");
		firstStep("FIRST");
	}

	@Step(value = "Name is {value}")
	public void firstStep(String value) {
		innerStep();
		LOGGER.info("First step log");
	}

	@Step
	public void innerStep() {
		LOGGER.info("Inner step log");
	}
}
