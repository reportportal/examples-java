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

package com.epam.reportportal.example.testng.logback.callback;

import com.epam.reportportal.service.Launch;
import com.epam.reportportal.service.ReportPortalClient;
import com.epam.reportportal.service.tree.ItemTreeReporter;
import com.epam.reportportal.service.tree.TestItemTree;
import com.epam.reportportal.testng.util.ItemTreeUtils;
import com.epam.ta.reportportal.ws.model.FinishTestItemRQ;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Calendar;

import static com.epam.reportportal.testng.TestNGService.ITEM_TREE;
import static java.util.Optional.ofNullable;

/**
 * An example of retrieving the last test item and sending a log to it after the test finish. The test works only if
 * <code>rp.reporting.callback</code> property set to <code>true</code>.
 */
public class CallbackReportingTest {

	@Test
	public void first() {
		Assert.assertEquals(1, 1);
	}

	@AfterMethod
	public void after(ITestResult testResult) {
		ItemTreeUtils.retrieveLeaf(testResult, ITEM_TREE).ifPresent(testResultLeaf -> {
			sendLog(testResultLeaf);
			sendFinishRequest(testResultLeaf, testResult.getName().equals("third") ? "PASSED" : "FAILED");
		});

	}

	private static ReportPortalClient getClient() {
		return ofNullable(Launch.currentLaunch()).map(Launch::getClient)
				.orElseThrow(() -> new IllegalStateException("Unable to get Report Portal Client"));
	}

	private void sendLog(TestItemTree.TestItemLeaf testResultLeaf) {
		ItemTreeReporter.sendLog(
				getClient(),
				"ERROR",
				"Callback log",
				Calendar.getInstance().getTime(),
				ITEM_TREE.getLaunchId(),
				testResultLeaf
		);
	}

	private void sendFinishRequest(TestItemTree.TestItemLeaf testResultLeaf, String status) {
		FinishTestItemRQ finishTestItemRQ = new FinishTestItemRQ();
		finishTestItemRQ.setStatus(status);
		finishTestItemRQ.setEndTime(Calendar.getInstance().getTime());
		ItemTreeReporter.finishItem(getClient(), finishTestItemRQ, ITEM_TREE.getLaunchId(), testResultLeaf)
				.cache()
				.blockingGet();
	}
}
