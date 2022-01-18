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

package com.epam.reportportal.example.junit5.nested.step;

import com.epam.reportportal.annotations.Step;
import com.epam.reportportal.junit5.ReportPortalExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple test that simulates nested structure of the `Nested Steps` with multiple nested levels.
 * Test methods call `red` or `green` methods that call each other to create multi-level nested structure.
 *
 * @author <a href="mailto:ivan_budayeu@epam.com">Ivan Budayeu</a>
 */
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(ReportPortalExtension.class)
public class AnnotationNestedStepsDemoTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(AnnotationNestedStepsDemoTest.class);

	private static final String METHODS_NAME_TEMPLATE = "My name is - `{method}` and I am a nested step of the `{parent}`";

	private static final ThreadLocal<Integer> COUNTER = new InheritableThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return 0;
		}
	};

	@BeforeEach
	public void beforeEach() {
		first();
	}

	@Test
	void firstTest() {
		LOGGER.error("yeah");
		greenNestedStep("firstTest");
	}

	@Test
	void secondTest() {
		redNestedStep("secondTest");
	}

	@Step(value = METHODS_NAME_TEMPLATE)
	public void greenNestedStep(String parent) {
		LOGGER.error("hello");
		if (COUNTER.get() == 5) {
			return;
		}
		COUNTER.set(COUNTER.get() + 1);
		redNestedStep("greenNestedStep");

	}

	@Step(value = "My name is - `{method}` and I am a nested step of the `{parent}`")
	public void redNestedStep(String parent) {
		LOGGER.error("hello");
		greenNestedStep("redNestedStep");
	}

	@Step
	public void first() {
		LOGGER.error("hello");
		second();
	}

	@Step
	public void second() {
		LOGGER.error("hello");
		third();
	}

	@Step
	public void third() {
		LOGGER.error("hello");
	}

}
