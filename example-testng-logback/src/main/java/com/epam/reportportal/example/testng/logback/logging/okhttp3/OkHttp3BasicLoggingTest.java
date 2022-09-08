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

import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.okhttp3.ReportPortalOkHttp3LoggingInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * A basic example which logs a simple request / response to Report Portal.
 */
public class OkHttp3BasicLoggingTest {

	private OkHttpClient client;

	/**
	 * Set {@link ReportPortalOkHttp3LoggingInterceptor} as one of the OkHttp3 interceptors.
	 */
	@BeforeClass
	public void setupOkHttp3() {
		client = new OkHttpClient.Builder().addInterceptor(new ReportPortalOkHttp3LoggingInterceptor(LogLevel.INFO))
				.followRedirects(true)
				.build();
	}

	/**
	 * Make a simple request to a test API and validate the response. Request / Response logs should appear on Report Portal.
	 */
	@Test
	public void okHttp3LoggingTest() throws IOException {
		Request request = new Request.Builder().url("https://jsonplaceholder.typicode.com/todos/1").get().build();
		try (Response response = client.newCall(request).execute()) {
			assertThat(response.code(), equalTo(200));
			ResponseBody responseBody = response.body();
			assertThat(responseBody, notNullValue());
			assertThat(responseBody.string(), not(emptyOrNullString()));
		}
	}
}
