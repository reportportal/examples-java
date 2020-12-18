package com.epam.reportportal.example.junit.callback;

import com.epam.reportportal.junit.ParallelRunningContext;
import com.epam.reportportal.junit.ReportPortalListener;
import com.epam.reportportal.junit.utils.ItemTreeUtils;
import com.epam.reportportal.service.ReportPortalClient;
import com.epam.reportportal.service.tree.ItemTreeReporter;
import com.epam.reportportal.service.tree.TestItemTree;
import com.epam.ta.reportportal.ws.model.FinishTestItemRQ;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author <a href="mailto:ivan_budayeu@epam.com">Ivan Budayeu</a>
 */
public class CallbackTest {

	private static final List<TestItemTree.TestItemLeaf> testItemLeaves = new ArrayList<TestItemTree.TestItemLeaf>();

	@Rule
	public TestRule rule = new TestWatcher() {
		@Override
		protected void finished(Description description) {
			TestItemTree.TestItemLeaf testItemLeaf = ItemTreeUtils.retrieveLeaf(description,
					ParallelRunningContext.getCurrent().getItemTree()
			);
			if (testItemLeaf != null) {
				testItemLeaves.add(testItemLeaf);
			}

		}
	};

	@Test
	public void passingTest() {
		assertTrue("This test shouldn't fail", true);
	}

	@AfterClass
	public static void afterClass() {
		ReportPortalClient client = ReportPortalListener.getReportPortal().getClient();
		TestItemTree itemTree = ParallelRunningContext.getCurrent().getItemTree();
		attachLog(client, itemTree);
		changeStatus(client, itemTree);
	}

	private static void attachLog(ReportPortalClient client, TestItemTree itemTree) {
		ItemTreeReporter.sendLog(client,
				"ERROR",
				"Error message",
				Calendar.getInstance().getTime(),
				itemTree.getLaunchId(),
				testItemLeaves.get(0)
		);
	}

	private static void changeStatus(ReportPortalClient client, TestItemTree itemTree) {
		FinishTestItemRQ finishTestItemRQ = new FinishTestItemRQ();
		finishTestItemRQ.setStatus("FAILED");
		finishTestItemRQ.setEndTime(Calendar.getInstance().getTime());
		ItemTreeReporter.finishItem(client, finishTestItemRQ, itemTree.getLaunchId(), testItemLeaves.get(0))
				.cache()
				.ignoreElement()
				.blockingAwait();
	}
}
