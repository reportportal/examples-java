/*
 * Copyright 2020 EPAM Systems
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

package com.epam.reportportal.example.testng.logback.extension;

import com.epam.reportportal.listeners.ItemStatus;
import com.epam.reportportal.testng.BaseTestNGListener;
import com.epam.reportportal.testng.TestMethodType;
import com.epam.reportportal.testng.TestNGService;
import com.epam.ta.reportportal.ws.model.FinishTestItemRQ;
import com.epam.ta.reportportal.ws.model.StartTestItemRQ;
import com.epam.ta.reportportal.ws.model.attribute.ItemAttributesRQ;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Extension example
 *
 * @author Andrei Varabyeu
 */
@Listeners({ ParametersAsAttributesTest.ExtendedListener.class })
public class ParametersAsAttributesTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParametersAsAttributesTest.class);

	@Test(threadPoolSize = 2, dataProvider = "bla-bla")
	public void testParams(String msg) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			LOGGER.info(msg + ": " + i);
			if (i == 1) {
				Thread.sleep(TimeUnit.SECONDS.toMillis(5L));
			}
		}
	}

	@DataProvider(parallel = true, name = "bla-bla")
	public Iterator<Object[]> params() {
		return Arrays.asList(new Object[] { "one" }, new Object[] { "two" }).iterator();
	}

	public static class ExtendedListener extends BaseTestNGListener {
		public ExtendedListener() {
			super(new ParamTaggingTestNgService());
		}

		@Override
		public void onTestStart(ITestResult testResult) {
			System.out.println(testResult.getMethod().getInvocationCount());

			System.out.println(testResult.getMethod().getCurrentInvocationCount());
			super.onTestStart(testResult);
		}
	}

	public static class ParamTaggingTestNgService extends TestNGService {

		@Override
		protected StartTestItemRQ buildStartStepRq(final @Nonnull ITestResult testResult, final @Nonnull TestMethodType type) {
			final StartTestItemRQ rq = super.buildStartStepRq(testResult, type);
			if (testResult.getParameters() != null && testResult.getParameters().length != 0) {
				Set<ItemAttributesRQ> attributes = Optional.ofNullable(rq.getAttributes()).orElse(new HashSet<>());
				for (Object param : testResult.getParameters()) {
					attributes.add(new ItemAttributesRQ(null, param.toString()));
				}
				rq.setAttributes(attributes);

			}
			return rq;
		}

		@Override
		protected FinishTestItemRQ buildFinishTestMethodRq(ItemStatus status, ITestResult testResult) {
			FinishTestItemRQ finishTestItemRQ = super.buildFinishTestMethodRq(status, testResult);
			if (testResult.getThrowable() != null) {
				String description = "```error\n" + ExceptionUtils.getStackTrace(testResult.getThrowable()) + "\n```";
				description = description + ExceptionUtils.getStackTrace(testResult.getThrowable());
				finishTestItemRQ.setDescription(description);
			}
			return finishTestItemRQ;
		}
	}

}
