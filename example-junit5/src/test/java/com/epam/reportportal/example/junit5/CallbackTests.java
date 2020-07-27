package com.epam.reportportal.example.junit5;

import com.epam.reportportal.junit5.ReportPortalExtension;
import com.epam.reportportal.junit5.utils.ItemTreeUtils;
import com.epam.reportportal.service.tree.ItemTreeReporter;
import com.epam.reportportal.service.tree.TestItemTree;
import com.epam.ta.reportportal.ws.model.FinishTestItemRQ;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Calendar;

import static com.epam.reportportal.junit5.ReportPortalExtension.REPORT_PORTAL;
import static com.epam.reportportal.junit5.ReportPortalExtension.TEST_ITEM_TREE;

/**
 * @author <a href="mailto:ivan_budayeu@epam.com">Ivan Budayeu</a>
 */
@ExtendWith(ReportPortalExtension.class)
public class CallbackTests {

	@Nested
	class FirstContext {

		@RepeatedTest(2)
		void nestedTest() {
			Assertions.assertEquals("first", "second");
		}

	}

	@Test
	void someTest() {
		Assertions.assertEquals(1, 1);
	}

	@AfterEach
	void afterMethod(TestInfo testInfo) {
		TestItemTree.TestItemLeaf testItemLeaf = ItemTreeUtils.retrieveLeaf(testInfo, TEST_ITEM_TREE);
		if (testItemLeaf != null) {
			attachLog(testItemLeaf);
			finishWithStatus(testInfo.getDisplayName().contains("someTest") ? "FAILED" : "PASSED", testItemLeaf);
		}
	}

	private static void attachLog(TestItemTree.TestItemLeaf testItemLeaf) {
		ItemTreeReporter.sendLog(REPORT_PORTAL.getClient(),
				"ERROR",
				"Error message",
				Calendar.getInstance().getTime(),
				TEST_ITEM_TREE.getLaunchId(),
				testItemLeaf
		);
	}

	private void finishWithStatus(String status, TestItemTree.TestItemLeaf testItemLeaf) {
		FinishTestItemRQ finishTestItemRQ = new FinishTestItemRQ();
		finishTestItemRQ.setStatus(status);
		finishTestItemRQ.setEndTime(Calendar.getInstance().getTime());
		ItemTreeReporter.finishItem(REPORT_PORTAL.getClient(), finishTestItemRQ, TEST_ITEM_TREE.getLaunchId(), testItemLeaf)
				.cache()
				.subscribe();
	}
}
