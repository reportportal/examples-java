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

import com.epam.reportportal.formatting.http.converters.DefaultHttpHeaderConverter;
import com.epam.reportportal.formatting.http.converters.SanitizingHttpHeaderConverter;
import com.epam.reportportal.httpcomponents.ReportPortalHttpLoggingInterceptor;
import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.utils.files.Utils;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * A basic example which logs a simple request / response to Report Portal.
 */
public class HttpComponentsFormTest {

	private final ReportPortalHttpLoggingInterceptor logger = new ReportPortalHttpLoggingInterceptor(LogLevel.INFO,
			SanitizingHttpHeaderConverter.INSTANCE,
			DefaultHttpHeaderConverter.INSTANCE
	);

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
		HttpPost request = new HttpPost("https://example.com/api/test");
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(Arrays.asList(new BasicNameValuePair("username", "user"),
				new BasicNameValuePair("password", "password"),
				new BasicNameValuePair("grant_type", "password")
		));
		request.setEntity(entity);
		request.addHeader(new BasicHeader(HttpHeaders.AUTHORIZATION, "Bearer test_token"));

		HttpResponse response = client.execute(request);
		assertThat(response.getStatusLine().getStatusCode(), equalTo(404));
		HttpEntity responseBody = response.getEntity();
		assertThat(responseBody, notNullValue());
		assertThat(Utils.readInputStreamToString(responseBody.getContent()), not(emptyOrNullString()));
	}
}
