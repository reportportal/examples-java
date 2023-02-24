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

package com.epam.reportportal.example.testng.logback.annotated;

import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.TestCaseIdKey;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:ivan_budayeu@epam.com">Ivan Budayeu</a>
 */
@SuppressWarnings("unused")
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

	@TestCaseId("{method}-{0}")
	@Test(dataProvider = "numbersProvider")
	public void parametrizedTestTemplate(String testCaseId, Integer expected, Integer evaluated) {
		Assert.assertEquals(expected, evaluated);
	}
}
