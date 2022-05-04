package com.epam.reportportal.example.junit.logging;

import com.epam.reportportal.example.junit.LoggingUtils;
import com.epam.reportportal.example.junit.MagicRandomizer;
import com.google.common.io.Resources;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Logs image
 *
 * @author Andrei Varabyeu
 */
public class LuckyPugTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(LuckyPugTest.class);

	@Test
	public void logImageBase64() throws IOException {

		/* Generate 10 logs with pugs. Pug may be lucky or unlucky based on randomizer */
		for (int i = 0; i < 20; i++) {
			/* 50 percents. So we should have approximately same count of lucky and unlucky pugs */
			boolean happy = MagicRandomizer.checkYourLucky(30);
			String image = getImageResource(happy);
			LoggingUtils.log(Resources.asByteSource(Resources.getResource(image)).read(), "Pug is " + (happy ? "HAPPY" : "NOT HAPPY"));
		}
	}

	private String getImageResource(boolean lucky) {
		return "pug/" + (lucky ? "lucky.jpg" : "unlucky.jpg");
	}
}
