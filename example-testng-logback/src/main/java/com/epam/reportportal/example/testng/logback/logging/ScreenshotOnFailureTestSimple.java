/*
 * Copyright 2021 EPAM Systems
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

package com.epam.reportportal.example.testng.logback.logging;

import com.epam.reportportal.example.testng.logback.LoggingUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * The simplest way to report a screenshot on a test failure. Reports screenshot as a separate log entry in 'After method' method.
 */
public class ScreenshotOnFailureTestSimple {

	private WebDriver driver;

	@BeforeMethod
	public void initDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@Test
	public void testTitle() {
		driver.navigate().to("https://www.example.com");
		Assert.assertEquals(driver.getTitle(), "Google");
	}

	@AfterMethod
	public void screenshotOnFailure(ITestResult result) {
		if (!result.isSuccess()) {
			if (driver instanceof TakesScreenshot) {
				String screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				LoggingUtils.logBase64(screenshot, "Invalid page");
			}
		}
		driver.quit();
	}
}
