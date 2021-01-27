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

package com.epam.reportportal.example.cucumber.reporter;

import com.epam.reportportal.cucumber.ScenarioReporter;
import com.epam.ta.reportportal.ws.model.StartTestItemRQ;
import gherkin.formatter.model.Match;
import gherkin.formatter.model.Scenario;
import gherkin.formatter.model.Step;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * A customized reporter which reports Scenarios as statistic items and a custom tag as Test Case ID
 */
public class ScenarioReporterTestCaseId extends ScenarioReporter {

	public static final String TAG_PREFIX = "@";
	public static final String TEST_TRACKING_TICKET_PREFIX = "JIRA";

	// Getting our custom tag from already parsed attributes and set it as Test Case ID for the item
	@Override
	protected StartTestItemRQ buildStartScenarioRequest(Scenario scenario, String uri) {
		StartTestItemRQ rq = super.buildStartScenarioRequest(scenario, uri);
		rq.getAttributes()
				.stream()
				.filter(a -> isNotBlank(a.getValue()) && a.getValue().startsWith(TAG_PREFIX + TEST_TRACKING_TICKET_PREFIX))
				.findAny()
				.ifPresent(t -> rq.setTestCaseId(t.getValue()
						.substring(TAG_PREFIX.length()))); // strip tag prefix to make Test Case ID equal to real ticket ID
		return rq;
	}

	// Removing Test Case ID from nested steps as redundant
	@Override
	protected StartTestItemRQ buildStartStepRequest(Step step, String stepPrefix, Match match) {
		StartTestItemRQ rq = super.buildStartStepRequest(step, stepPrefix, match);
		rq.setTestCaseId(null);
		return rq;
	}
}
