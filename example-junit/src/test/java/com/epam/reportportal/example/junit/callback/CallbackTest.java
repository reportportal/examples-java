package com.epam.reportportal.example.junit.callback;

import com.epam.reportportal.junit.utils.ItemTreeUtils;
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

import static com.epam.reportportal.junit.JUnitProvider.ITEM_TREE;
import static com.epam.reportportal.junit.JUnitProvider.REPORT_PORTAL;
import static org.junit.Assert.assertTrue;

/**
 * @author <a href="mailto:ivan_budayeu@epam.com">Ivan Budayeu</a>
 */
public class CallbackTest {

	private static List<TestItemTree.TestItemLeaf> testItemLeaves = new ArrayList<TestItemTree.TestItemLeaf>();

	@Rule
	public TestRule rule = new TestWatcher() {
		@Override
		protected void finished(Description description) {
			TestItemTree.TestItemLeaf testItemLeaf = ItemTreeUtils.retrieveLeaf(description, ITEM_TREE);
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
		attachLog();
		changeStatus();
	}

	private static void attachLog() {
		ItemTreeReporter.sendLog(REPORT_PORTAL.getClient(),
				"ERROR",
				"Error message",
				Calendar.getInstance().getTime(),
				testItemLeaves.get(0)
		);
	}

	private static void changeStatus() {
		FinishTestItemRQ finishTestItemRQ = new FinishTestItemRQ();
		finishTestItemRQ.setStatus("FAILED");
		finishTestItemRQ.setEndTime(Calendar.getInstance().getTime());
		ItemTreeReporter.finishItem(REPORT_PORTAL.getClient(), finishTestItemRQ, ITEM_TREE.getLaunchId(), testItemLeaves.get(0))
				.cache()
				.ignoreElement()
				.blockingAwait();
	}
}
