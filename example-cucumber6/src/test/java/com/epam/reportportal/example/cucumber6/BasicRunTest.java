package com.epam.reportportal.example.cucumber6;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = "com.epam.reportportal.cucumber.ScenarioReporter",
		features = "src/test/resources/features/attributes", glue = "com.epam.reportportal.example.cucumber6.attributes")
public class BasicRunTest {
}
