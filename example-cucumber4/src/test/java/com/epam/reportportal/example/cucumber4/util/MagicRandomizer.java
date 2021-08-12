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

package com.epam.reportportal.example.cucumber4.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.base.Preconditions;
import com.google.common.collect.Range;

import java.util.Random;

/**
 * Just a randomizer
 *
 * @author Andrei Varabyeu
 */
public class MagicRandomizer {

	private static final Logger LOGGER = LoggerFactory.getLogger(MagicRandomizer.class);

	private static final Range<Integer> PROBABILITY_RANGE = Range.openClosed(0, 100);

	private static final Random RANDOM = new Random();

	private MagicRandomizer() {
		//statics only
	}

	public static int luckyInt(int bound) {
		return RANDOM.nextInt(bound);
	}

	/**
	 * Just put probability and check your luckyness
	 *
	 * @param probability value [0--100]
	 * @return TRUE if you are really lucky!
	 */
	public static boolean checkYourLucky(int probability) {
		Preconditions.checkArgument(PROBABILITY_RANGE.contains(probability), "%s is not in range [%s]", probability, PROBABILITY_RANGE);

		boolean lucky = Range.closedOpen(PROBABILITY_RANGE.lowerEndpoint(), probability)
				.contains(luckyInt(PROBABILITY_RANGE.upperEndpoint()));
		LOGGER.debug("Generating [TRUE/FALSE] with probability {}%. Result {}", probability, lucky);
		return lucky;
	}
}
