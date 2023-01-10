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

import com.epam.reportportal.listeners.ListenerParameters;
import com.epam.reportportal.service.Launch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static java.util.Optional.ofNullable;

/**
 * An example how to get and report current Launch URL
 */
public class LaunchUrlLoggingTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(LaunchUrlLoggingTest.class);

	@Test
	public void emptyTest() {
		LOGGER.info("Just an empty test");
	}

	@AfterClass
	public void logLaunchUrl() {
		Launch launch = ofNullable(Launch.currentLaunch()).orElseThrow(() -> new IllegalStateException(
				"Launch not found"));
		ListenerParameters parameters = launch.getParameters();
		String launchUuid = launch.getLaunch().blockingGet();
		String baseUrl = parameters.getBaseUrl();
		baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
		LOGGER.info("Launch URL: {}/ui/#{}/launches/all/{}", baseUrl, parameters.getProjectName(), launchUuid);
	}
}
