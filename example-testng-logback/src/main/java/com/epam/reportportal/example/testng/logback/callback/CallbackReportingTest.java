package com.epam.reportportal.example.testng.logback.callback;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author <a href="mailto:ivan_budayeu@epam.com">Ivan Budayeu</a>
 */
public class CallbackReportingTest {

	@Test
	public void first(ITestContext testContext) throws IOException {

	}

//	@AfterMethod
//	public void after(ITestResult testResult) {
//		TestItemTree.TestItemLeaf testResultLeaf = ItemTreeUtils.retrieveLeaf(testResult, ITEM_TREE);
//		if (testResultLeaf != null) {
//			//			ItemTreeReporter.sendLog(REPORT_PORTAL.getClient(),
//			//					"ERROR",
//			//					"Callback log",
//			//					Calendar.getInstance().getTime(),
//			//					new File("/Users/yumfriez/IdeaProjects/example-java-TestNG/logback/src/main/resources/files/payload.json"),
//			//					testResultLeaf
//			//			);
//
//			FinishTestItemRQ finishTestItemRQ = new FinishTestItemRQ();
//			finishTestItemRQ.setStatus("FAILED");
//			finishTestItemRQ.setEndTime(Calendar.getInstance().getTime());
//			finishItem(REPORT_PORTAL.getClient(), finishTestItemRQ, ITEM_TREE.getLaunchId(), testResultLeaf).cache().subscribe();
//		}
//
//	}
}
