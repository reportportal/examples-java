package com.epam.reportportal.example.testng.log4j.logging;

import com.epam.reportportal.example.testng.log4j.MagicRandomizer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import com.google.common.io.BaseEncoding;
import com.google.common.io.Resources;

import java.io.IOException;

/**
 * Logs image
 *
 * @author Andrei Varabyeu
 */
public class LuckyPugTest {

	private static final Logger LOGGER = LogManager.getLogger(LuckyPugTest.class);

	@Test
	public void logImageBase64() throws IOException {

		/* Generate 10 logs with pugs. Pug may be lucky or unlucky based on randomizer */
		for (int i = 0; i < 20; i++) {
			/* 50 percents. So we should have approximately same count of lucky and unlucky pugs */
			boolean happy = MagicRandomizer.checkYourLucky(30);
			String image = getImageResource(happy);

			LOGGER.info(
					"RP_MESSAGE#BASE64#{}#{}",
					BaseEncoding.base64().encode(Resources.asByteSource(Resources.getResource(image)).read()),
					"Pug is " + (happy ? "HAPPY" : "NOT HAPPY")
			);
		}

	}

	private String getImageResource(boolean lucky) {
		return "pug/" + (lucky ? "lucky.jpg" : "unlucky.jpg");
	}
}
