package com.epam.reportportal.example.cucumber3.logging;


import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;
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
    public void beforeStep(){
        LOGGER.info("Before each step");
    }

    @AfterStep
    public void afterStep(){
        LOGGER.info("After each step");
    }
}
