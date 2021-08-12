package com.epam.reportportal.example.cucumber2.logging;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class Hooks {
	private static final Logger LOGGER = LoggerFactory.getLogger(Hooks.class);

	private List<String> storage;

	@Before
	public void beforeScenario() {
		LOGGER.info("Inside before");
		LOGGER.info("Initial storage state: {}", storage);
		storage = Arrays.asList("one", "two");
		LOGGER.info("Storage is set to {}", storage);
	}

	@Given("^I report scenario with hooks$")
	public void test() {
		LOGGER.info("Storage contains: {}", storage);
	}

	@After
	public void afterScenario() {
		LOGGER.info("Inside after");
		LOGGER.info("Storage state: {}", storage);
		storage = null;
		LOGGER.info("Final storage state: {}", storage);
	}
}
