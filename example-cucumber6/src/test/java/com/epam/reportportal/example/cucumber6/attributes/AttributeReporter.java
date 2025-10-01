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

package com.epam.reportportal.example.cucumber6.attributes;

import com.epam.reportportal.cucumber.ScenarioReporter;
import com.epam.reportportal.listeners.ListenerParameters;
import com.epam.ta.reportportal.ws.model.StartTestItemRQ;
import com.epam.ta.reportportal.ws.model.attribute.ItemAttributeResource;
import com.epam.ta.reportportal.ws.model.attribute.ItemAttributesRQ;
import com.epam.ta.reportportal.ws.model.launch.StartLaunchRQ;
import io.cucumber.plugin.event.TestCase;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import java.net.URI;
import java.time.Instant;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Attribute Reporter to demonstrate attribute customization for different cases.
 * <p>
 * Case 1: Add custom attributes to the Launch on its start.
 * Case 2: Process key-value attributes for scenarios.
 */
public class AttributeReporter extends ScenarioReporter {
	public static final String TAG_PREFIX = "@";
	public static final String ATTRIBUTE_VALUE_SEPARATOR = ":";
	public static final String TEST_TRACKING_TICKET_PREFIX = "JIRA";

	/**
	 * Use this method if you need to add custom attributes on your launch before it start.
	 *
	 * @return a map of attributes
	 */
	private Map<String, String> getCustomLaunchAttributes() {
		return Collections.singletonMap("custom-launch-attribute-name", "custom-launch-attribute-value");
	}

	/**
	 * Override <code>buildStartLaunchRq</code> method to add custom attributes to the launch.
	 *
	 * @param startTime  start time of the launch
	 * @param parameters listener parameters
	 * @return StartLaunchRQ object
	 */
	@Override
	protected StartLaunchRQ buildStartLaunchRq(Instant startTime, ListenerParameters parameters) {
		StartLaunchRQ rq = super.buildStartLaunchRq(startTime, parameters);
		getCustomLaunchAttributes().forEach((key, value) -> rq.getAttributes().add(new ItemAttributesRQ(key, value)));
		return rq;
	}

	/**
	 * Process all attributes for scenarios.
	 *
	 * @param rq StartTestItemRQ object
	 */
	private static void processAttributes(StartTestItemRQ rq) {
		rq.setAttributes(rq.getAttributes().stream().map(ItemAttributeResource::getValue).filter(StringUtils::isNotBlank).map(attr -> {
			if (attr.contains(ATTRIBUTE_VALUE_SEPARATOR)) {
				// split attribute value by separator and create an attribute object with key-value
				String[] parts = attr.split(ATTRIBUTE_VALUE_SEPARATOR, 2);
				// Also strip tag prefix (usually redundant '@')
				return new ItemAttributesRQ(parts[0].trim().substring(TAG_PREFIX.length()), parts[1].trim());
			} else if (attr.startsWith(TAG_PREFIX + TEST_TRACKING_TICKET_PREFIX)) {
				// set Test Case ID if attribute starts with a specific prefix
				rq.setTestCaseId(attr.substring(TAG_PREFIX.length())); // strip tag prefix to make Test Case ID equal to real ticket ID
				return null;
			} else {
				// Leave attribute as Tag in all other cases
				return new ItemAttributesRQ(null, attr.substring(TAG_PREFIX.length())); // strip tag prefix (usually redundant '@')
			}
		}).filter(Objects::nonNull).collect(Collectors.toUnmodifiableSet()));
	}

	/**
	 * Override <code>buildStartScenarioRequest</code> method to process Test Case ID and key-value attributes for scenarios.
	 *
	 * @param testCase test case
	 * @param name     scenario name
	 * @param uri      scenario uri
	 * @param line     scenario line
	 * @return StartTestItemRQ object
	 */
	@Override
	@Nonnull
	protected StartTestItemRQ buildStartScenarioRequest(@Nonnull TestCase testCase, @Nonnull String name, @Nonnull URI uri, int line) {
		StartTestItemRQ rq = super.buildStartScenarioRequest(testCase, name, uri, line);
		processAttributes(rq);
		return rq;
	}
}
