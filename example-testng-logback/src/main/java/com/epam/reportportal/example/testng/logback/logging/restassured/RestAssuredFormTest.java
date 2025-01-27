/*
 * Copyright 2025 EPAM Systems
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.epam.reportportal.example.testng.logback.logging.restassured;

import com.epam.reportportal.formatting.http.converters.*;
import com.epam.reportportal.formatting.http.entities.Param;
import com.epam.reportportal.listeners.ListenerParameters;
import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.restassured.ReportPortalRestAssuredLoggingFilter;
import com.epam.reportportal.service.Launch;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static java.util.Optional.ofNullable;

/**
 * An example of a form data request logging.
 */
public class RestAssuredFormTest {

	/**
	 * A converter that hides the value of the "password" form parameter and passes the rest of the parameters to default form parameter
	 * converter.
	 */
	private static final Function<Param, String> SANITIZING_PARAM_CONVERTER = new Function<>() {
		@Override
		public @Nullable String apply(@Nullable Param param) {
			return DefaultFormParamConverter.INSTANCE.apply(ofNullable(param).filter(p -> "password".equalsIgnoreCase(p.getName()))
					.map(p -> {
						Param newParam = p.clone();
						newParam.setValue("<removed>");
						return newParam;
					})
					.orElse(param));
		}
	};

	/**
	 * Create a prettifier for JSON content type that hides the value of the "access_token" and "refresh_token" fields and updates the
	 * prettifier map.
	 *
	 * @param prettifiers original prettifier map
	 * @return updated prettifier map
	 */
	@Nonnull
	private static Map<String, Function<String, String>> getUpdatedPrettifiersMap(@Nonnull Map<String, Function<String, String>> prettifiers) {
		Map<String, Function<String, String>> myPrettifiers = new HashMap<>(prettifiers);
		Function<String, String> jsonPrettifier = myPrettifiers.get("application/json");
		Function<String, String> jsonSanitizer = json -> jsonPrettifier.apply(json.replaceAll(
						"access_token\"(\\s*):(\\s*)\"[^\"]*\"",
						"access_token\"$1:$2\"<removed>\""
				)
				.replaceAll("refresh_token\"(\\s*):(\\s*)\"[^\"]*\"", "refresh_token\"$1:$2\"<removed>\""));
		myPrettifiers.put("application/json", jsonSanitizer);
		return myPrettifiers;
	}

	/**
	 * Set {@link ReportPortalRestAssuredLoggingFilter} as one of the REST Assured filters.
	 */
	@BeforeClass
	public void setupRestAssured() {
		RestAssured.reset(); // Reset everything to avoid collisions with other REST Assured examples
		ReportPortalRestAssuredLoggingFilter logger = new ReportPortalRestAssuredLoggingFilter(
				42,
				LogLevel.INFO,
				SanitizingHttpHeaderConverter.INSTANCE,
				DefaultHttpHeaderConverter.INSTANCE,
				DefaultCookieConverter.INSTANCE,
				DefaultUriConverter.INSTANCE,
				SANITIZING_PARAM_CONVERTER
		);
		logger.setContentPrettifiers(getUpdatedPrettifiersMap(logger.getContentPrettifiers()));
		RestAssured.filters(logger);
	}

	/**
	 * Make a Form Data request to a test API and validate the response. Request / Response logs should appear on Report Portal.
	 */
	@Test
	public void restAssuredLoggingTest() {
		ListenerParameters parameters = ofNullable(Launch.currentLaunch()).map(Launch::getParameters)
				.orElseThrow(() -> new IllegalStateException("Launch is not started"));
		RestAssured.given()
				.header("Authorization", "Basic " + Base64.getEncoder().encodeToString("ui:uiman".getBytes(StandardCharsets.UTF_8)))
				.formParam("username", "default")
				.formParam("password", "1q2w3e")
				.formParam("grant_type", "password")
				.post(parameters.getBaseUrl() + "/uat/sso/oauth/token")
				.then()
				.assertThat()
				.statusCode(200);
	}
}
