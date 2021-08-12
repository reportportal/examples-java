package com.epam.reportportal.example.junit5.attribute;

import com.epam.reportportal.junit5.utils.ItemTreeUtils;
import com.epam.reportportal.service.tree.ItemTreeReporter;
import com.epam.reportportal.service.tree.TestItemTree;
import com.epam.ta.reportportal.ws.model.FinishTestItemRQ;
import com.epam.ta.reportportal.ws.model.attribute.ItemAttributesRQ;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.collect.Sets;

import java.util.Calendar;

import static com.epam.reportportal.junit5.ReportPortalExtension.REPORT_PORTAL;
import static com.epam.reportportal.junit5.ReportPortalExtension.TEST_ITEM_TREE;
import static java.util.Optional.ofNullable;

/**
 * @author <a href="mailto:ihar_kahadouski@epam.com">Ihar Kahadouski</a>
 */
public class SaucelabsAttributeTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(SaucelabsAttributeTest.class);

	@Test
	void stepWithSauceLabsAttribute() {
		LOGGER.info("SauceLabs attribute with job id will be added in after method");
	}

	@AfterEach
	void after(TestInfo testInfo) {
		ofNullable(ItemTreeUtils.retrieveLeaf(testInfo, TEST_ITEM_TREE)).ifPresent(SaucelabsAttributeTest::addSaucelabsAttribute);
	}

	private static void addSaucelabsAttribute(TestItemTree.TestItemLeaf leaf) {
		FinishTestItemRQ request = new FinishTestItemRQ();
		request.setStatus("passed");
		request.setEndTime(Calendar.getInstance().getTime());
		request.setAttributes(Sets.newHashSet(new ItemAttributesRQ("SLID", "0586c1c90fcd4a499591109692426d54")));
		ItemTreeReporter.finishItem(REPORT_PORTAL.getClient(), request, TEST_ITEM_TREE.getLaunchId(), leaf).cache().subscribe();
	}
}