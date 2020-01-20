package com.epam.reportportal.example.jbehave.steps;

import org.jbehave.core.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogLevelTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogLevelTest.class);

	@BeforeScenario(uponType = ScenarioType.ANY)
	public void beforeScenario() {
		LOGGER.info("Before Scenario");
	}

	@AfterScenario(uponType = ScenarioType.ANY)
	public void afterScenario() {
		LOGGER.info("After Scenario");
	}

	@BeforeStories()
	public void beforeStories() {
		LOGGER.info("Before stories");
	}

	@AfterStories()
	public void afterStories() {
		LOGGER.info("After stories");
	}

	@Given("Log level test")
	public void logLevelTest() {
		LOGGER.info("It is log level test in Background");
	}

	@Given("I emit log on level info")
	public void infoLevelTest() {
		LOGGER.info("Info level test");
	}

	@Given("I emit log on level error")
	public void errorLevelTest() {
		LOGGER.error("Error level test");
	}

	@Given("I emit log on level debug")
	public void debugLevelTest() {
		LOGGER.debug("Debug level test");
	}

	@Given("I emit log on level warn")
	public void warnLevelTest() {
		LOGGER.warn("Warn level test");
	}

	@Given("I emit log on level trace")
	public void traceLevelTest() {
		LOGGER.trace("Trace level test");
	}
}
