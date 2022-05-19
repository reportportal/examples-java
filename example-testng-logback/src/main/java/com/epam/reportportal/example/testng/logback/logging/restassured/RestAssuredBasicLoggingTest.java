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
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * A basic example which logs a simple REST Assured request / response to Report Portal.
 */
public class RestAssuredBasicLoggingTest {

	/**
	 * Set {@link ReportPortalRestAssuredLoggingFilter} as one of the REST Assured filters.
	 */
	@BeforeSuite
	public void setupRestAssured() {
		RestAssured.reset(); // Reset everything to avoid collisions with other REST Assured examples
		RestAssured.filters(new ReportPortalRestAssuredLoggingFilter(42, LogLevel.INFO));
	}

	/**
	 * Make a simple request to a test API and validate the response. Request / Response logs should appear on Report Portal.
	 */
	@Test
	public void restAssuredLoggingTest() {
		JsonPath bodyResult = RestAssured.given()
				.get("https://jsonplaceholder.typicode.com/todos/1")
				.then()
				.assertThat()
				.statusCode(200)
				.extract()
				.body()
				.jsonPath();
		assertThat("Verify ID", bodyResult.getInt("id"), equalTo(1));
		assertThat("Verify task is not completed", bodyResult.getBoolean("completed"), equalTo(Boolean.FALSE));
	}
}
