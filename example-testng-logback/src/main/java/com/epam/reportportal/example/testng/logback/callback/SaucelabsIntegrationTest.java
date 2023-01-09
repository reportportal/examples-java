/*
 * Copyright 2021 EPAM Systems
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
import com.epam.ta.reportportal.ws.model.attribute.ItemAttributesRQ;
import com.google.common.collect.Sets;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import static com.epam.reportportal.testng.TestNGService.ITEM_TREE;
import static java.util.Optional.ofNullable;

/**
 * An example of updating the last test item with Saucelabs `SLID` attribute necessary to make Saucelabs plugin works.
 * The test works only if <code>rp.reporting.callback</code> property set to <code>true</code>.
 */
public class SaucelabsIntegrationTest {

	private static final String SAUCELABS_USERNAME_PARAMETER = "SAUCE_USERNAME";
	private static final String SAUCELABS_ACCESS_KEY_PARAMETER = "SAUCE_ACCESS_KEY";

	// Put your username
	private static final String username = null;
	// Put your access key
	private static final String accessKey = null;

	//EU central
	private static final String sauceDataCenter = "ondemand.eu-central-1.saucelabs.com";

	private RemoteWebDriver driver;

	private static String getSystemProperty(String key) {
		return System.getProperty(key);
	}

	private static String getEnvironmentVariable(String key) {
		return System.getenv(key);
	}

	private static String getParameter(String parameterName) {
		return ofNullable(getSystemProperty("SAUCE_USERNAME")).orElseGet(() -> ofNullable(getEnvironmentVariable(
				"SAUCE_USERNAME")).orElseThrow(() -> new RuntimeException(
				"Parameter '" + parameterName + "' was not found")));
	}

	@BeforeMethod
	public void initDriver() throws MalformedURLException {
		/*
		 * In this section, we will configure our SauceLabs credentials in order to run our tests on saucelabs.com
		 */
		String sauceUserName = ofNullable(username).orElseGet(() -> getParameter(SAUCELABS_USERNAME_PARAMETER));
		String sauceAccessKey = ofNullable(accessKey).orElseGet(() -> getParameter(SAUCELABS_ACCESS_KEY_PARAMETER));

		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("username", sauceUserName);
		capabilities.setCapability("accessKey", sauceAccessKey);
		capabilities.setCapability("browserName", "Safari");
		capabilities.setCapability("platform", "macOS 10.13");
		capabilities.setCapability("version", "11.1");
		capabilities.setCapability("build", "Report Portal Saucelabs integration example");
		capabilities.setCapability("name", "shouldOpenSafari");

		/*
		 * Create a new Remote driver that will allow your test to send commands to the Sauce Labs grid so that Sauce
		 * can execute your tests
		 */
		driver = new RemoteWebDriver(new URL("https://" + sauceDataCenter + "/wd/hub"), capabilities);
	}

	@Test
	public void shouldOpenSafari() {
		driver.navigate().to("https://www.saucedemo.com");
		Assert.assertTrue(true);
	}

	@AfterMethod
	public void cleanUpAfterTestMethod(ITestResult result) {
		((JavascriptExecutor) driver).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
		SessionId sessionId = driver.getSessionId();
		ItemTreeUtils.retrieveLeaf(result, ITEM_TREE)
				.ifPresent(testResultLeaf -> sendFinishRequest(testResultLeaf,
						ofNullable(sessionId).map(SessionId::toString).orElse("")
				));
		driver.quit();
	}

	private static ReportPortalClient getClient() {
		return ofNullable(Launch.currentLaunch()).map(Launch::getClient)
				.orElseThrow(() -> new IllegalStateException("Unable to get Report Portal Client"));
	}

	private void sendFinishRequest(TestItemTree.TestItemLeaf testResultLeaf, String sauceLabsJobId) {
		FinishTestItemRQ finishTestItemRQ = new FinishTestItemRQ();
		finishTestItemRQ.setStatus("PASSED");
		finishTestItemRQ.setEndTime(Calendar.getInstance().getTime());
		finishTestItemRQ.setAttributes(Sets.newHashSet(new ItemAttributesRQ("SLID", sauceLabsJobId)));
		ItemTreeReporter.finishItem(getClient(), finishTestItemRQ, ITEM_TREE.getLaunchId(), testResultLeaf)
				.cache()
				.blockingGet();
	}
}
