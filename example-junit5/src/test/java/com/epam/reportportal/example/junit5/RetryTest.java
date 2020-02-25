package com.epam.reportportal.example.junit5;

import org.junit.jupiter.api.RepeatedTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author <a href="mailto:ihar_kahadouski@epam.com">Ihar Kahadouski</a>
 */
public class RetryTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(RetryTest.class);
	private static Random RANDOM = new Random();

	@RepeatedTest(3)
	void retry() {
		if (RANDOM.nextBoolean()) {
			LOGGER.info("passed");
		} else {
			LOGGER.info("failed");
			fail();
		}
	}
}
