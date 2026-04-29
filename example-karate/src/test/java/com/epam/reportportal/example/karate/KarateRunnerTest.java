/*
 * Copyright 2026 EPAM Systems
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.epam.reportportal.example.karate;

import com.epam.reportportal.karate.ReportPortalRunListener;
import io.karatelabs.core.Runner;
import io.karatelabs.core.SuiteResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KarateRunnerTest {

	@Test
	public void testAll() {
		ReportPortalRunListener karateRunHook = new ReportPortalRunListener(); // Initialize ReportPortal runtime Karate listener
		SuiteResult results = Runner // Regular Karate runner
				.path("classpath:features") // Path with feature files
				.listener(karateRunHook) // Add Karate listener
				.outputCucumberJson(true) // Generate cucumber report
				.tags("~@ignore") // Ignore tests marked with the tag
				.parallel(2); // Run in 2 Threads
		// Here you can additionally run tests, retries, etc.
		karateRunHook.finishLaunch(); // Finish execution on ReportPortal
		Assertions.assertEquals(0, results.getFeatureFailedCount(), "Non-zero fail count.\n Errors:\n" + results.getErrors());
	}
}
