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

import com.epam.reportportal.cucumber.AbstractReporter;
import com.epam.reportportal.listeners.ListenerParameters;
import io.cucumber.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LaunchLinkLog {
	private static final Logger LOGGER = LoggerFactory.getLogger(LaunchLinkLog.class);

	@Given("I log the Launch link")
	public void logLaunchLink() {
		ListenerParameters parameters = AbstractReporter.getCurrent().getLaunch().getParameters();
		String launchUuid = AbstractReporter.getCurrent().getItemTree().getLaunchId().blockingGet();

		LOGGER.info("Launch URL: {}/ui/#{}/launches/all/{}", parameters.getBaseUrl(), parameters.getProjectName(), launchUuid);
	}
}
