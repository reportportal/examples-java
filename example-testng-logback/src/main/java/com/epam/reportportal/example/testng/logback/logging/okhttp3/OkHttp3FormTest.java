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

package com.epam.reportportal.example.testng.logback.logging.okhttp3;

import com.epam.reportportal.formatting.http.converters.DefaultHttpHeaderConverter;
import com.epam.reportportal.formatting.http.converters.SanitizingHttpHeaderConverter;
import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.okhttp3.ReportPortalOkHttp3LoggingInterceptor;
import okhttp3.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * An example of a form data request logging.
 */
public class OkHttp3FormTest {

	private OkHttpClient client;

	/**
	 * Set {@link ReportPortalOkHttp3LoggingInterceptor} as one of the OkHttp3 interceptors.
	 */
	@BeforeClass
	public void setupOkHttp3() {
		client = new OkHttpClient.Builder().addInterceptor(new ReportPortalOkHttp3LoggingInterceptor(
				LogLevel.INFO,
				SanitizingHttpHeaderConverter.INSTANCE,
				DefaultHttpHeaderConverter.INSTANCE
		)).followRedirects(true).build();
	}

	/**
	 * Make a Form Data request to a test API and validate the response. Request / Response logs should appear on Report Portal.
	 */
	@Test
	public void okHttp3LoggingTest() throws IOException {
		RequestBody requestBody = new FormBody.Builder().add("username", "user")
				.add("password", "password")
				.add("grant_type", "password")
				.build();
		Request request = new Request.Builder().url("https://example.com/api/test")
				.header("Authorization", "Bearer test_token")
				.post(requestBody)
				.build();
		try (Response response = client.newCall(request).execute()) {
			assertThat(response.code(), equalTo(404));
			ResponseBody responseBody = response.body();
			assertThat(responseBody, notNullValue());
			assertThat(responseBody.string(), not(emptyOrNullString()));
		}
	}
}
