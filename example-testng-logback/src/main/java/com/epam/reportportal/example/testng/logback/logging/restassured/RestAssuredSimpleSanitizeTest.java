/*
 * Copyright 2022 EPAM Systems
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

package com.epam.reportportal.example.testng.logback.logging.restassured;

import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.restassured.ReportPortalRestAssuredLoggingFilter;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * An example of a header credentials hiding in case they contain sensitive data.
 */
public class RestAssuredSimpleSanitizeTest {

	/**
	 * Create Rest Assured Logging Config with hidden headers.
	 */
	private static final RestAssuredConfig CONFIG = RestAssuredConfig.config()
			.logConfig(LogConfig.logConfig().blacklistHeader("Authorization"));

	/**
	 * Set {@link ReportPortalRestAssuredLoggingFilter} as one of the REST Assured filters.
	 */
	@BeforeClass
	public void setupRestAssured() {
		RestAssured.reset(); // Reset everything to avoid collisions with other REST Assured examples
		RestAssured.filters(new ReportPortalRestAssuredLoggingFilter(42, LogLevel.INFO));
	}

	/**
	 * Make a simple request to a test API and validate the response. Sanitized Request / Response logs should appear on Report Portal.
	 */
	@Test
	public void restAssuredLoggingTest() {
		RestAssured.given()
				.config(CONFIG)
				.header("Authorization", "Bearer test_token")
				.get("https://jsonplaceholder.typicode.com/todos/1")
				.then()
				.assertThat()
				.statusCode(200);
	}
}