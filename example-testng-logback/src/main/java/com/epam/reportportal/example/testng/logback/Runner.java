/*
 * Copyright 2020 EPAM Systems
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

package com.epam.reportportal.example.testng.logback;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.testng.ITestContext;
import org.testng.ITestNGListener;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Andrei Varabyeu
 */
public class Runner {

	public static void main(String[] args) {

		boolean hasFailures = run("example-testng-logback/suites/logging_tests.xml");
		//		if (hasFailures) {
		//			System.setProperty("rp.rerun", "true");
		//			run("test-output/testng-failed.xml");
		//		}
		System.exit(hasFailures ? 1 : 0);

	}

	public static boolean run(String xml) {
		TestNG testNG = new TestNG(true);
		List<Class<? extends ITestNGListener>> classes = new ArrayList<>();
		classes.add(ReportPortalTestNGListener.class);

		testNG.setListenerClasses(classes);

		testNG.setTestSuites(Arrays.asList(xml));

		TestListenerAdapter results = new TestListenerAdapter() {
			@Override
			public void onStart(ITestContext testContext) {
				super.onStart(testContext);
			}
		};
		testNG.addListener((ITestNGListener) results);
		boolean hasFailures;
		try {
			testNG.run();
			hasFailures = results.getFailedTests().size() > 0 || results.getSkippedTests().size() > 0;
		} catch (Throwable e) {
			/* something goes wrong... */
			e.printStackTrace();
			hasFailures = true;

		}
		return hasFailures;
	}

}
