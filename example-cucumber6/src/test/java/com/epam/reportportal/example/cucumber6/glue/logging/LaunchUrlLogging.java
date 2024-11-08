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

package com.epam.reportportal.example.cucumber6.glue.logging;

import com.epam.reportportal.example.cucumber6.BasicRunTest;
import com.epam.reportportal.listeners.ListenerParameters;
import com.epam.reportportal.service.Launch;
import io.cucumber.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Optional.ofNullable;

/**
 * An example how to get and report current Launch URL, run {@link BasicRunTest} class to see results.
 */
public class LaunchUrlLogging {
	private static final Logger LOGGER = LoggerFactory.getLogger(LaunchUrlLogging.class);

	@Given("I log the Launch link")
	public void logLaunchLink() {
		ofNullable(Launch.currentLaunch()).ifPresent(l -> {
			ListenerParameters parameters = l.getParameters();
			String launchUuid = l.getLaunch().blockingGet();
			LOGGER.info("Launch URL: {}/ui/#{}/launches/all/{}", parameters.getBaseUrl(), parameters.getProjectName(), launchUuid);
		});
	}
}
