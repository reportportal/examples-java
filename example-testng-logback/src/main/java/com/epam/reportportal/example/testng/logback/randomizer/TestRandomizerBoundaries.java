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

package com.epam.reportportal.example.testng.logback.randomizer;

import com.epam.reportportal.example.testng.logback.util.MagicRandomizer;
import org.testng.annotations.Test;

/**
 * Tests for Randomizer boundaries
 *
 * @author Andrei Varabyeu
 */
public class TestRandomizerBoundaries {

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testLowerBoundExceeded() {
		MagicRandomizer.checkYourLucky(-1);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testHigherBoundExceeded() {
		MagicRandomizer.checkYourLucky(101);
	}

	@Test
	public void testHigherBound() {
		MagicRandomizer.checkYourLucky(100);
	}

	@Test
	public void testLowerBound() {
		MagicRandomizer.checkYourLucky(1);
	}
}
