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

package com.epam.reportportal.example.junit5.logging;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.epam.reportportal.example.junit5.ScreenShootOnFailureExtension;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A test with extension which makes screenshots with Selenide on test failure.
 */
@ExtendWith(ScreenShootOnFailureExtension.class)
public class SelenideScreenshotLoggingTest {

	@BeforeAll
	public static void initDriver() {
		WebDriverManager.chromedriver().setup();
		Configuration.browser = Browsers.CHROME;
	}

	@Test
	public void testTitle() {
		Selenide.open("https://www.example.com");
		assertEquals("Google", Selenide.title());
	}

	@AfterEach
	public void tearDown() {
		Selenide.closeWebDriver();
	}
}
