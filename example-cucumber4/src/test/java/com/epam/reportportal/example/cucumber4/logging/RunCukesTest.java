package com.epam.reportportal.example.cucumber4.logging;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty",
		"com.epam.reportportal.cucumber.StepReporter" }, features = "src/test/resources/com/epam/reportportal/example/cucumber4/logging", tags = "not @ignore")
public class RunCukesTest {
}
