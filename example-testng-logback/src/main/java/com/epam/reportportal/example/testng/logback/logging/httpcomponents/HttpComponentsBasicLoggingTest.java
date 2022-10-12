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

package com.epam.reportportal.example.testng.logback.logging.httpcomponents;

import com.epam.reportportal.httpcomponents.ReportPortalHttpLoggingInterceptor;
import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.utils.files.Utils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * A basic example which logs a simple request / response to Report Portal.
 */
public class HttpComponentsBasicLoggingTest {

	private final ReportPortalHttpLoggingInterceptor logger = new ReportPortalHttpLoggingInterceptor(LogLevel.INFO);

	private HttpClient client;

	/**
	 * Set {@link ReportPortalHttpLoggingInterceptor} as one of the HttpComponents interceptors.
	 */
	@BeforeClass
	public void setupHttpClient() {
		client = HttpClientBuilder.create()
				.addInterceptorLast((HttpRequestInterceptor) logger)
				.addInterceptorLast((HttpResponseInterceptor) logger)
				.setRedirectStrategy(new LaxRedirectStrategy())
				.build();
	}

	/**
	 * Make a simple request to a test API and validate the response. Request / Response logs should appear on Report Portal.
	 */
	@Test
	public void httpClientLoggingTest() throws IOException {
		HttpUriRequest request = new HttpGet("https://jsonplaceholder.typicode.com/todos/1");
		HttpResponse response = client.execute(request);
		assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
		HttpEntity responseBody = response.getEntity();
		assertThat(responseBody, notNullValue());
		assertThat(Utils.readInputStreamToString(responseBody.getContent()), not(emptyOrNullString()));
	}
}
