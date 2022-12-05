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
import com.epam.reportportal.listeners.ListenerParameters;
import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.service.Launch;
import com.epam.reportportal.utils.files.Utils;
import com.epam.ta.reportportal.ws.model.Constants;
import io.reactivex.Maybe;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicHeader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Collections;

import static java.util.Optional.ofNullable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * An example of a multipart request logging.
 */
public class HttpComponentsMultipartTest {

	private static final String REQUEST_URL_PATTERN = "%s/api/v2/%s/log";
	private static final String REQUEST_PATTERN = "[{\"file\": {\"name\": \"test.pdf\"},\"launchUuid\": \"%s\",\"level\": \"FATAL\",\"message\": \"custom\",\"time\": \"2021-11-11T14:35:55.006Z\"}]";

	private final ReportPortalHttpLoggingInterceptor logger = new ReportPortalHttpLoggingInterceptor(LogLevel.INFO,
			SanitizingHttpHeaderConverter.INSTANCE,
			DefaultHttpHeaderConverter.INSTANCE
	);

	private ListenerParameters parameters;
	private HttpClient client;

	/**
	 * Set {@link ReportPortalHttpLoggingInterceptor} as one of the HttpComponents interceptors.
	 */
	@BeforeClass
	public void setupHttpClient() {
		parameters = ofNullable(Launch.currentLaunch()).map(Launch::getParameters)
				.orElseThrow(() -> new IllegalStateException(
						"Unable to get ReportPortal parameters, please ensure it's initialized"));

		client = HttpClientBuilder.create()
				.addInterceptorLast((HttpRequestInterceptor) logger)
				.addInterceptorLast((HttpResponseInterceptor) logger)
				.setRedirectStrategy(new LaxRedirectStrategy())
				.setDefaultHeaders(Collections.singleton(new BasicHeader(HttpHeaders.AUTHORIZATION,
						"Bearer " + parameters.getApiKey()
				)))
				.build();
	}

	/**
	 * Make a Multipart request to an RP API and validate the response. Request / Response logs should appear on Report Portal.
	 */
	@Test
	public void httpClientLoggingTest() throws IOException {
		String launchUuid = ofNullable(Launch.currentLaunch()).map(Launch::getLaunch)
				.map(Maybe::blockingGet)
				.orElseThrow(() -> new AssertionError("Unable to get Launch UUID"));
		String baseUrl = parameters.getBaseUrl();
		baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
		HttpPost request = new HttpPost(String.format(REQUEST_URL_PATTERN,
				baseUrl,
				parameters.getProjectName()
		));
		HttpEntity requestBody = MultipartEntityBuilder.create()
				.addTextBody(Constants.LOG_REQUEST_JSON_PART,
						String.format(REQUEST_PATTERN, launchUuid),
						ContentType.APPLICATION_JSON
				)
				.addBinaryBody(Constants.LOG_REQUEST_BINARY_PART,
						ofNullable(Thread.currentThread()
								.getContextClassLoader()
								.getResourceAsStream("files/test.pdf")).orElseThrow(() -> new IllegalArgumentException(
								"Unable to find test file")),
						ContentType.create("application/pdf"),
						"test.pdf"
				)
				.build();
		request.setEntity(requestBody);
		HttpResponse response = client.execute(request);
		assertThat(response.getStatusLine().getStatusCode(), equalTo(201));
		HttpEntity responseBody = response.getEntity();
		assertThat(responseBody, notNullValue());
		assertThat(Utils.readInputStreamToString(responseBody.getContent()), not(emptyOrNullString()));
	}
}
