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

package com.epam.reportportal.example.testng.logback.extension;

import com.epam.reportportal.example.testng.logback.LoggingUtils;
import com.epam.reportportal.listeners.ItemStatus;
import com.epam.reportportal.testng.BaseTestNGListener;
import com.epam.reportportal.testng.TestNGService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * More advanced way to report a screenshot. Reports screenshot as a separate log entry in 'Test' method.
 */
@Listeners({ ScreenshotOnFailureTestExtension.ExtendedListener.class })
public class ScreenshotOnFailureTestExtension {

	private static WebDriver driver;

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
	public void quitDriver() {
		driver.quit();
	}

	public static class ExtendedListener extends BaseTestNGListener {
		public ExtendedListener() {
			super(new ScreenshotUploadService());
		}
	}

	public static class ScreenshotUploadService extends TestNGService {
		@Override
		public void finishTestMethod(ItemStatus status, ITestResult testResult) {
			if (!testResult.isSuccess()) {
				if (driver instanceof TakesScreenshot) {
					String screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					LoggingUtils.logBase64(screenshot, "Invalid page");
				}
			}
			super.finishTestMethod(status, testResult);
		}
	}
}
