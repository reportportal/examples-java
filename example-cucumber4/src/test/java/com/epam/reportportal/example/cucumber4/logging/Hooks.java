package com.epam.reportportal.example.cucumber4.logging;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rp.com.google.common.collect.Lists;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Hooks {
	private static final Logger LOGGER = LoggerFactory.getLogger(Hooks.class);

	private List<String> storage;

	@Before
	public void beforeScenario() {
		LOGGER.info("Inside before");
		LOGGER.info("Initial storage state: {}", storage);
		storage = Lists.newArrayList("one", "two");
		LOGGER.info("Storage is set to {}", storage);
	}

	@BeforeStep
	public void beforeStep() {
		LOGGER.info("Before step");
	}

	@Given("I report scenario with hooks")
	public void test() {
		LOGGER.info("Storage contains: {}", storage);
	}

	@AfterStep
	public void afterStep() {
		LOGGER.info("After step");
	}

	@After
	public void afterScenario(Scenario scenario) throws IOException {
		LOGGER.info("Inside after");
		LOGGER.info("Storage state: {}", storage);
		storage = null;
		LOGGER.info("Final storage state: {}", storage);
		takeScreenshot(scenario);
	}

	private void takeScreenshot(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			byte[] screenshot = IOUtils.toByteArray(Objects.requireNonNull(getClass().getClassLoader()
					.getResourceAsStream("files/file.png")));
			scenario.embed(screenshot, "image/png");
		}
	}
}
