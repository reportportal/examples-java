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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Some of BeforeXXX should fail. Except you are incredibly lucky!
 *
 * @author Andrei Varabyeu
 */
public class FailedBeforesTest {

	private Logger LOGGER = LoggerFactory.getLogger(FailedBeforesTest.class);

	@BeforeClass
	public void noChancesBeforeClass() {
		LOGGER.info("Are you lucky?");
		Assert.assertEquals(MagicRandomizer.luckyInt(20), 1, "It was not so many chances, that this method pass");
	}

	@BeforeMethod
	public void fiftyFiftyBeforeMethod() {
		LOGGER.info("You have 50% chances!");
		Assert.assertEquals(MagicRandomizer.luckyInt(10), 5, "You are not lucky, man!");
	}

	@Test
	public void someSeriousTest1() {
		Assert.assertEquals("be or not to be", "b||!2b");
	}

}
