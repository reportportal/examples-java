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

package com.epam.reportportal.example.cucumber2;

import com.epam.reportportal.example.cucumber2.reporter.ScenarioReporterTestCaseId;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * A JUnit runner for Cucumber which is using customized {@link ScenarioReporterTestCaseId} reporter.
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "com.epam.reportportal.example.cucumber2.reporter.ScenarioReporterTestCaseId" }, features = {
		"src/test/resources/features/attribute" }, glue = { "com.epam.reportportal.example.cucumber2.attribute" })
public class RunCukesTestScenarioReporter {
}
