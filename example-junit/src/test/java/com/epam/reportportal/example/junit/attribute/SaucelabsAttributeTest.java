package com.epam.reportportal.example.junit.attribute;

import com.epam.reportportal.junit.utils.ItemTreeUtils;
import com.epam.reportportal.service.tree.ItemTreeReporter;
import com.epam.reportportal.service.tree.TestItemTree;
import com.epam.ta.reportportal.ws.model.FinishTestItemRQ;
import com.epam.ta.reportportal.ws.model.attribute.ItemAttributesRQ;
import com.google.common.collect.Sets;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.epam.reportportal.junit.ParallelRunningContext.ITEM_TREE;
import static com.epam.reportportal.junit.ParallelRunningHandler.REPORT_PORTAL;
import static java.util.Optional.ofNullable;

/**
 * @author <a href="mailto:ihar_kahadouski@epam.com">Ihar Kahadouski</a>
 */
public class SaucelabsAttributeTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(SaucelabsAttributeTest.class);
	private static List<TestItemTree.TestItemLeaf> testItemLeaves = new ArrayList<>();

	@Rule
	public TestRule rule = new TestWatcher() {
		@Override
		protected void finished(Description description) {
			ofNullable(ItemTreeUtils.retrieveLeaf(description, ITEM_TREE)).ifPresent(testItemLeaves::add);
		}
	};

	@Test
	public void stepWithSauceLabsAttribute() {
		LOGGER.info("SauceLabs job id will be added as attribute in after class method");
	}

	@AfterClass
	public static void afterClass() {
		addSaucelabsAttribute();
	}

	private static void addSaucelabsAttribute() {
		FinishTestItemRQ request = new FinishTestItemRQ();
		request.setEndTime(Calendar.getInstance().getTime());
		request.setStatus("PASSED");
		request.setAttributes(Sets.newHashSet(new ItemAttributesRQ("SLID", "0586c1c90fcd4a499591109692426d54")));
		ItemTreeReporter.finishItem(REPORT_PORTAL.getClient(), request, ITEM_TREE.getLaunchId(), testItemLeaves.get(0))
				.cache()
				.ignoreElement()
				.blockingAwait();
	}
}
