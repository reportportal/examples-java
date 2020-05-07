package com.epam.reportportal.example.testng.logback.attribute;

import com.epam.reportportal.service.tree.ItemTreeReporter;
import com.epam.reportportal.service.tree.TestItemTree;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import com.epam.reportportal.testng.TestNGService;
import com.epam.reportportal.testng.util.ItemTreeUtils;
import com.epam.ta.reportportal.ws.model.FinishTestItemRQ;
import com.epam.ta.reportportal.ws.model.attribute.ItemAttributesRQ;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Calendar;

import static com.epam.reportportal.testng.TestNGService.ITEM_TREE;

/**
 * @author <a href="mailto:ivan_budayeu@epam.com">Ivan Budayeu</a>
 */
@Listeners(ReportPortalTestNGListener.class)
public class DynamicAttributeTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(DynamicAttributeTest.class);

	@Test
	void stepWithSauceLabsAttribute() {
		LOGGER.info("SauceLabs attribute with job id will be added in after method");
	}

	@AfterMethod
	public void after(ITestResult testResult) {
		ItemTreeUtils.retrieveLeaf(testResult, ITEM_TREE).ifPresent(this::sendFinishRequest);

	}

	private void sendFinishRequest(TestItemTree.TestItemLeaf testResultLeaf) {
		FinishTestItemRQ finishTestItemRQ = new FinishTestItemRQ();
		finishTestItemRQ.setStatus("FAILED");
		finishTestItemRQ.setEndTime(Calendar.getInstance().getTime());
		finishTestItemRQ.setAttributes(Sets.newHashSet(new ItemAttributesRQ("SLID", "0586c1c90fcd4a499591109692426d54")));
		ItemTreeReporter.finishItem(TestNGService.getReportPortal().getClient(), finishTestItemRQ, ITEM_TREE.getLaunchId(), testResultLeaf)
				.cache()
				.blockingGet();
	}
}
