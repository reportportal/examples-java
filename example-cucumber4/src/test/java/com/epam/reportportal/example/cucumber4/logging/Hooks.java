package com.epam.reportportal.example.cucumber4.logging;


import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {
	private static final Logger LOGGER = LoggerFactory.getLogger(Hooks.class);

	@Before
	public void beforeScenario() {
		LOGGER.info("Before scenario");
	}

	@After
	public void afterScenario() {
		LOGGER.info("After scenario");
	}

	@BeforeStep
	public void beforeStep() {
		LOGGER.info("Before each step");
	}

	@AfterStep
	public void afterStep() {
		LOGGER.info("After each step");
	}
}
