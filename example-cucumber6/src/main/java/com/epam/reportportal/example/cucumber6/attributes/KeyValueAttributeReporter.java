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
import com.epam.ta.reportportal.ws.model.StartTestItemRQ;
import com.epam.ta.reportportal.ws.model.attribute.ItemAttributeResource;
import com.epam.ta.reportportal.ws.model.attribute.ItemAttributesRQ;
import io.cucumber.plugin.event.TestCase;

import javax.annotation.Nonnull;
import java.net.URI;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Attribute Reporter to demonstrate attribute customization for different cases.
 */
public class KeyValueAttributeReporter extends ScenarioReporter {
	public static final String ATTRIBUTE_VALUE_SEPARATOR = ":";

	private static void processKeyValueAttributes(StartTestItemRQ rq) {
		rq.setAttributes(rq.getAttributes().stream().map(ItemAttributeResource::getValue).filter(Objects::nonNull).map(attr -> {
			if (attr.contains(ATTRIBUTE_VALUE_SEPARATOR)) {
				String[] parts = attr.split(ATTRIBUTE_VALUE_SEPARATOR, 2);
				return new ItemAttributesRQ(parts[0].trim(), parts[1].trim());
			} else {
				return new ItemAttributesRQ(null, attr);
			}
		}).collect(Collectors.toUnmodifiableSet()));
	}

	@Override
	@Nonnull
	protected StartTestItemRQ buildStartScenarioRequest(@Nonnull TestCase testCase, @Nonnull String name, @Nonnull URI uri, int line) {
		StartTestItemRQ rq = super.buildStartScenarioRequest(testCase, name, uri, line);
		processKeyValueAttributes(rq);
		return rq;
	}
}
