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

import com.epam.reportportal.formatting.http.converters.DefaultHttpHeaderConverter;
import com.epam.reportportal.formatting.http.converters.SanitizingCookieConverter;
import com.epam.reportportal.formatting.http.converters.SanitizingHttpHeaderConverter;
import com.epam.reportportal.formatting.http.converters.SanitizingUriConverter;
import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.restassured.ReportPortalRestAssuredLoggingFilter;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * An example of a header, cookies and URI credentials hiding in case they contain sensitive data.
 */
public class RestAssuredSanitizeTest {

	/**
	 * Set {@link ReportPortalRestAssuredLoggingFilter} as one of the REST Assured filters.
	 */
	@BeforeClass
	public void setupRestAssured() {
		RestAssured.reset(); // Reset everything to avoid collisions with other REST Assured examples
		RestAssured.filters(new ReportPortalRestAssuredLoggingFilter(
				42,
				LogLevel.INFO,
				SanitizingHttpHeaderConverter.INSTANCE,
				DefaultHttpHeaderConverter.INSTANCE,
				SanitizingCookieConverter.INSTANCE,
				SanitizingUriConverter.INSTANCE
		));
	}

	/**
	 * Make a simple request to a test API and validate the response. Request / Response logs should appear on Report Portal.
	 */
	@Test
	public void restAssuredLoggingTest() {
		RestAssured.given()
				.header("Authorization", "Bearer test_token")
				.cookie(new Cookie.Builder("session_id", "test_session_id").build())
				.get("https://user:password@jsonplaceholder.typicode.com/todos/1")
				.then()
				.assertThat()
				.statusCode(200);
	}
}
