package com.epam.reportportal.example.cucumber2;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "com.epam.reportportal.cucumber.StepReporter" },
		features = {"src/test/resources/features/attribute"},
		glue = {"com.epam.reportportal.example.cucumber2.attribute"})
public class RunStepReporterTest {
}