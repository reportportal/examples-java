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

package com.epam.reportportal.example.testng.logback.logging;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.epam.reportportal.selenide.ReportPortalSelenideEventListener;
import org.openqa.selenium.By;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Level;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * A basic example which logs Selenide actions as nested steps into Report Portal. Fails intentionally, to show screenshot, page source and
 * Selenium log attach.
 */
public class SelenideLoggingTest {

	private static final By MY_NON_EXISTENT_LOCATOR = By.xpath("//button[@type='submit']");

	static {
		// enable Selenide logger and attach browser logs on step failure
		SelenideLogger.addListener(
				"ReportPortal logger",
				new ReportPortalSelenideEventListener().enableSeleniumLogs(LogType.BROWSER, Level.FINER)
		);
	}

	@Test
	public void testTitle() {
		open("https://www.example.com");
		Assert.assertEquals($(MY_NON_EXISTENT_LOCATOR).getText(), "Proceed");
	}
}
