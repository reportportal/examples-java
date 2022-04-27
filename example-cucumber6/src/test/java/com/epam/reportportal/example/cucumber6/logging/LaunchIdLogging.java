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

package com.epam.reportportal.example.cucumber6.logging;

import com.epam.reportportal.example.cucumber6.RunLoggingTests;
import com.epam.reportportal.service.Launch;
import com.epam.ta.reportportal.ws.model.launch.LaunchResource;
import io.cucumber.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Optional.ofNullable;

/**
 * An example how to get and report current Launch ID, run {@link RunLoggingTests} class to see results.
 */
public class LaunchIdLogging {
	private static final Logger LOGGER = LoggerFactory.getLogger(LaunchIdLogging.class);

	@Given("I log the Launch ID")
	public void logLaunchLink() {
		ofNullable(Launch.currentLaunch()).ifPresent(l -> {
			String launchUuid = l.getLaunch().blockingGet();
			LaunchResource launchInfo = l.getClient().getLaunchByUuid(launchUuid).blockingGet();
			LOGGER.info("Launch ID: {}", launchInfo.getLaunchId());
		});
	}
}
