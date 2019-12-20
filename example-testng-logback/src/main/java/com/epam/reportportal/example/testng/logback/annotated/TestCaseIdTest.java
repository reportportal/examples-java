package com.epam.reportportal.example.testng.logback.annotated;

import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.TestCaseIdKey;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:ivan_budayeu@epam.com">Ivan Budayeu</a>
 */
@Listeners(ReportPortalTestNGListener.class)
public class TestCaseIdTest {

	@TestCaseId("TNG.1.0.1")
	@Test
	public void simpleTest() {
		Assert.assertEquals(1, 1);
	}

	@DataProvider(name = "numbersProvider")
	public static Object[][] data() {
		return new Object[][] { { "TNG.1.0", 2, 2 }, { "TNG.1.1", 6, 6 }, { "TNG.1.2", 19, 20 } };
	}

	@TestCaseId(parametrized = true)
	@Test(dataProvider = "numbersProvider")
	public void parametrizedTestWithKey(@TestCaseIdKey String testCaseId, Integer expected, Integer evaluated) {
		Assert.assertEquals(expected, evaluated);
	}

	@Test(dataProvider = "numbersProvider")
	public void parametrizedTestWithoutKey(String testCaseId, Integer expected, Integer evaluated) {
		Assert.assertEquals(expected, evaluated);
	}
}
