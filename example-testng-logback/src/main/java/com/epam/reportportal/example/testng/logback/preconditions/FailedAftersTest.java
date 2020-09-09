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

package com.epam.reportportal.example.testng.logback.preconditions;

import com.epam.reportportal.example.testng.logback.MagicRandomizer;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Some of AfterXXX should fail. Except you are incredibly lucky!
 * @author Andrei Varabyeu
 */
public class FailedAftersTest {

	@AfterClass
	public void noCnancesAfterClass() {
		Assert.assertEquals(MagicRandomizer.luckyInt(20), 1, "It was not so many chances, that this method pass");
	}

	@AfterMethod
	public void fiftyFiftyAfterMethod() {
		Assert.assertEquals(MagicRandomizer.luckyInt(10), 5, "You are not lucky, man!");

	}

	@Test
	public void someSeriousTest1() {
		Assert.assertEquals(
				"You asked me once,\" said O'Brien, \"what was in Room 101. I told you that you knew the answer already. Everyone knows it. The thing that is in Room 101 is the worst thing in the world.",
				"You asked me once,\" said O'Brien, \"what was in Room 101. I told you that you knew the answer already. Everyone knows it. The thing that is in Room 101 is the worst thing in the world");
	}

	@Test
	public void someSeriousTest2() {
		Assert.assertEquals(4, 4);
	}

	@Test
	public void someSeriousTest3() {
		Assert.assertEquals(3, 3);
	}
}
