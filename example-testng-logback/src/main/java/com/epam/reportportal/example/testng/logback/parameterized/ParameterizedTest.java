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

package com.epam.reportportal.example.testng.logback.parameterized;

import com.epam.reportportal.annotations.ParameterKey;
import com.epam.reportportal.annotations.TestCaseId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * Parametrized test. You should get all parameters. Custom keys for parameters
 * Custom uniqueID.
 *
 * @author Pavel Bortnik
 */
public class ParameterizedTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParameterizedTest.class);

	@Test
	@Parameters({ "message" })
	@TestCaseId(value = "HOOOOAA-my-very-unique-id", parametrized = true)
	public void testParametersFromSuite(String msg) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			LOGGER.info(msg + ": " + i);
			if (i == 1) {
				Thread.sleep(TimeUnit.SECONDS.toMillis(1L));
			}
		}
	}

	@Test(threadPoolSize = 2, dataProvider = "bla-bla")
	public void testParametersFromDataProvider(@ParameterKey("my_great_parameter") String msg, String msg2) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			LOGGER.info(msg + ": " + msg2);
			if (i == 1) {
				Thread.sleep(TimeUnit.SECONDS.toMillis(5L));
			}
		}
	}

	@DataProvider(parallel = true, name = "bla-bla")
	public Iterator<Object[]> params() {
		return Arrays.asList(new Object[] { "one", "two" }, new Object[] { "two", "one" }, new Object[] { "three", null }).iterator();
	}

}
