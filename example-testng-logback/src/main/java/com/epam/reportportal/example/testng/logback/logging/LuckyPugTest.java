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

package com.epam.reportportal.example.testng.logback.logging;

import com.epam.reportportal.example.testng.logback.util.LoggingUtils;
import com.epam.reportportal.example.testng.logback.util.MagicRandomizer;
import com.epam.reportportal.utils.files.Utils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * Logs image
 *
 * @author Andrei Varabyeu
 */
public class LuckyPugTest {
	@Test
	public void logImageBase64() throws IOException {

		/* Generate 10 logs with pugs. Pug may be lucky or unlucky based on randomizer */
		for (int i = 0; i < 20; i++) {
			/* 50 percents. So we should have approximately same count of lucky and unlucky pugs */
			boolean happy = MagicRandomizer.checkYourLucky(30);
			String image = getImageResource(happy);
			LoggingUtils.log(Utils.getFileAsByteSource(new File(image)).read(), "Pug is " + (happy ? "HAPPY" : "NOT HAPPY"));
		}

	}

	private String getImageResource(boolean lucky) {
		return "src/main/resources/pug/" + (lucky ? "lucky.jpg" : "unlucky.jpg");
	}
}
