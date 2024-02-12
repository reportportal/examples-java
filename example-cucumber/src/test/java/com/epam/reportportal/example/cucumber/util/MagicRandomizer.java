package com.epam.reportportal.example.cucumber.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * Just a randomizer
 *
 * @author Andrei Varabyeu
 */
public class MagicRandomizer {

	private static final Logger LOGGER = LoggerFactory.getLogger(MagicRandomizer.class);

	private static final Random RANDOM = new Random();
	private static final int UPPER_LIMIT = 100;

	private MagicRandomizer() {
		//statics only
	}

	public static int luckyInt(int bound) {
		return RANDOM.nextInt(bound);
	}

	/**
	 * Just put probability and check your luckiness
	 *
	 * @param probability value [0--100]
	 * @return TRUE if you are really lucky!
	 */
	public static boolean checkYourLucky(int probability) {
		boolean lucky = luckyInt(UPPER_LIMIT + 1) <= probability;
		LOGGER.debug("Generating [TRUE/FALSE] with probability {}%. Result {}", probability, lucky);
		return lucky;
	}
}
