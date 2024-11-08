package com.epam.reportportal.example.cucumber6;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = { "pretty", "html:report.html", "com.epam.reportportal.example.cucumber6.attributes.KeyValueAttributeReporter" },
		features = "@rerun.txt",
		glue = "com.epam.reportportal.example.cucumber6.glue")
public class BasicReRunTest {
}
