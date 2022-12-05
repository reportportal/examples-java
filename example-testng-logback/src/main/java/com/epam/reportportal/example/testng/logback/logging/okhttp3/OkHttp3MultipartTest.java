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
import com.epam.reportportal.listeners.ListenerParameters;
import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.okhttp3.ReportPortalOkHttp3LoggingInterceptor;
import com.epam.reportportal.service.Launch;
import com.epam.reportportal.utils.files.Utils;
import com.epam.ta.reportportal.ws.model.Constants;
import io.reactivex.Maybe;
import okhttp3.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static java.util.Optional.ofNullable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * An example of a multipart request logging.
 */
public class OkHttp3MultipartTest {

	private static final String REQUEST_URL_PATTERN = "%s/api/v2/%s/log";
	private static final String REQUEST_PATTERN = "[{\"file\": {\"name\": \"test.pdf\"},\"launchUuid\": \"%s\",\"level\": \"FATAL\",\"message\": \"custom\",\"time\": \"2021-11-11T14:35:55.006Z\"}]";

	private OkHttpClient client;
	private ListenerParameters parameters;

	/**
	 * Set {@link ReportPortalOkHttp3LoggingInterceptor} as one of the OkHttp3 interceptors.
	 */
	@BeforeClass
	public void setupOkHttp3() {
		parameters = ofNullable(Launch.currentLaunch()).map(Launch::getParameters)
				.orElseThrow(() -> new IllegalStateException(
						"Unable to get ReportPortal parameters, please ensure it's initialized"));
		client = new OkHttpClient.Builder().addInterceptor(new ReportPortalOkHttp3LoggingInterceptor(LogLevel.INFO,
						SanitizingHttpHeaderConverter.INSTANCE,
						DefaultHttpHeaderConverter.INSTANCE
				))
				.authenticator((route, response) -> {
					String credential = "Bearer " + parameters.getApiKey();
					return response.request().newBuilder().header("Authorization", credential).build();
				})
				.followRedirects(true)
				.build();
	}

	/**
	 * Make a Multipart request to an RP API and validate the response. Request / Response logs should appear on Report Portal.
	 */
	@Test
	public void okHttp3LoggingTest() throws IOException {
		String launchUuid = ofNullable(Launch.currentLaunch()).map(Launch::getLaunch)
				.map(Maybe::blockingGet)
				.orElseThrow(() -> new AssertionError("Unable to get Launch UUID"));
		RequestBody requestBody = new MultipartBody.Builder().setType(MediaType.get("multipart/form-data"))
				.addFormDataPart(Constants.LOG_REQUEST_JSON_PART,
						null,
						RequestBody.create(String.format(REQUEST_PATTERN, launchUuid),
								MediaType.get("application/json; charset=utf-8")
						)
				)
				.addFormDataPart(Constants.LOG_REQUEST_BINARY_PART,
						"test.pdf",
						RequestBody.create(Utils.getFile(new File("files/test.pdf")).read(),
								MediaType.get("application/pdf")
						)
				)
				.build();
		String baseUrl = parameters.getBaseUrl();
		baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
		Request request = new Request.Builder().url(String.format(REQUEST_URL_PATTERN,
				baseUrl,
				parameters.getProjectName()
		)).post(requestBody).build();
		try (Response response = client.newCall(request).execute()) {
			assertThat(response.code(), equalTo(201));
			ResponseBody responseBody = response.body();
			assertThat(responseBody, notNullValue());
			assertThat(responseBody.string(), not(emptyOrNullString()));
		}
	}
}
