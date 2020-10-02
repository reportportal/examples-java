package com.epam.reportportal.example.cucumber4;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "com.epam.reportportal.cucumber.StepReporter" },
		features = {"src/test/resources/features/attribute"},
		glue = {"com.epam.reportportal.example.cucumber4.attribute"})
public class RunCukesTest {
}
