/*
 * Copyright 2022 EPAM Systems
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.epam.reportportal.example.cucumber6.selenium;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The simplest way to report a screenshot on a test failure. Reports screenshot as a separate log entry into 'After hooks' step.
 */
public class ScreenshotOnFailureHooks {
	private static final Logger LOGGER = LoggerFactory.getLogger(ScreenshotOnFailureHooks.class);

	private WebDriver driver;

	@Before
	public void beforeScenario() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@Given("I report scenario with screenshot")
	public void test() {
		driver.navigate().to("https://www.example.com");
		LOGGER.warn("A failure test for demonstration, check out 'After hooks' for the failure screenshot");
		Assert.assertEquals("Google", driver.getTitle());
	}

	@After
	public void afterScenario(Scenario scenario) {
		takeScreenshot(scenario);
		driver.quit();
	}

	private void takeScreenshot(Scenario scenario) {
		if (scenario.isFailed()) {
			if (driver instanceof TakesScreenshot) {
				scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png", "Screenshot");
			}
		}
	}
}
