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
import com.epam.reportportal.formatting.http.entities.Header;
import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.restassured.ReportPortalRestAssuredLoggingFilter;
import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.function.Function;

import static com.epam.reportportal.formatting.http.Constants.REMOVED_TAG;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static java.util.Optional.ofNullable;

/**
 * An example of a header, cookies and URI credentials hiding in case they contain sensitive data.
 */
public class RestAssuredAdvanceSanitizeTest {

	/**
	 * A converter that hides the value of the "Set-Cookie" headers and passes the rest of the headers to
	 * {@link SanitizingHttpHeaderConverter}.
	 */
	private static final Function<Header, String> SANITIZING_HTTP_HEADER_CONVERTER = new Function<>() {
		@Override
		public @Nullable String apply(@Nullable Header header) {
			return SanitizingHttpHeaderConverter.INSTANCE.apply(ofNullable(header).filter(h -> "Set-Cookie".equalsIgnoreCase(h.getName()))
					.map(h -> {
						Header newHeader = h.clone();
						newHeader.setValue(REMOVED_TAG);
						return newHeader;
					})
					.orElse(header));
		}
	};

	private WireMockServer wireMockServer;
	private int mockPort;

	/**
	 * Set {@link ReportPortalRestAssuredLoggingFilter} as one of the REST Assured filters.
	 */
	@BeforeClass
	public void setupRestAssured() {
		RestAssured.reset(); // Reset everything to avoid collisions with other REST Assured examples
		RestAssured.filters(new ReportPortalRestAssuredLoggingFilter(
				42,
				LogLevel.INFO,
				SANITIZING_HTTP_HEADER_CONVERTER,
				DefaultHttpHeaderConverter.INSTANCE,
				SanitizingCookieConverter.INSTANCE,
				SanitizingUriConverter.INSTANCE
		));
	}

	/**
	 * Start WireMock server before each test. Stub a response for the login request.
	 */
	@BeforeMethod
	public void startWireMock() {
		wireMockServer = new WireMockServer(options().dynamicPort());
		wireMockServer.stubFor(post("/auth/login").willReturn(ok().withHeader("Set-Cookie", "session_id=test_session_id; Path=/; HttpOnly")
				.withHeader("Set-Cookie", "scope=view-all, edit-self; Path=/; HttpOnly")));
		wireMockServer.start();
		mockPort = wireMockServer.port();
	}

	/**
	 * Make a simple request to a test API and validate the response. Request / Response logs should appear on Report Portal.
	 */
	@Test
	public void advanceRestAssuredLoggingTest() {
		RestAssured.given()
				.header("Authorization", "Basic " + Base64.getEncoder().encodeToString("ui:password".getBytes(StandardCharsets.UTF_8)))
				.post("http://user:password@localhost:" + mockPort + "/auth/login")
				.then()
				.assertThat()
				.statusCode(200);
	}

	/**
	 * Stop WireMock server after each test.
	 */
	@AfterMethod
	public void stopWireMock() {
		wireMockServer.stop();
	}
}
