package com.epam.reportportal.example.testng.logback.callback;

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
import static com.epam.reportportal.testng.TestNGService.REPORT_PORTAL;

/**
 * @author <a href="mailto:ivan_budayeu@epam.com">Ivan Budayeu</a>
 */
public class CallbackReportingTest {

	@Test
	public void first() {
		Assert.assertEquals(1, 1);
	}

	@AfterMethod
	public void after(ITestResult testResult) {
		TestItemTree.TestItemLeaf testResultLeaf = ItemTreeUtils.retrieveLeaf(testResult, ITEM_TREE);
		if (testResultLeaf != null) {
			sendLog(testResultLeaf);
			sendFinishRequest(testResultLeaf);
		}

	}

	private void sendLog(TestItemTree.TestItemLeaf testResultLeaf) {
		ItemTreeReporter.sendLog(REPORT_PORTAL.getClient(),
				"ERROR",
				"Callback log",
				Calendar.getInstance().getTime(),
				ITEM_TREE.getLaunchId(),
				testResultLeaf
		);
	}

	private void sendFinishRequest(TestItemTree.TestItemLeaf testResultLeaf) {
		FinishTestItemRQ finishTestItemRQ = new FinishTestItemRQ();
		finishTestItemRQ.setStatus("FAILED");
		finishTestItemRQ.setEndTime(Calendar.getInstance().getTime());
		ItemTreeReporter.finishItem(REPORT_PORTAL.getClient(), finishTestItemRQ, ITEM_TREE.getLaunchId(), testResultLeaf)
				.cache()
				.blockingGet();
	}
}
