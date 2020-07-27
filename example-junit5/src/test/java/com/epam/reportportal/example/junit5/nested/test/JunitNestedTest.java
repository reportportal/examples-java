/*
 * Copyright 2020 EPAM Systems
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

package com.epam.reportportal.example.junit5.nested.test;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JunitNestedTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(JunitNestedTest.class);

	@TestFactory
	Stream<DynamicNode> dynamicTestsWithContainers() {
		return Stream.of("A", "B", "C")
				.map(input -> DynamicContainer.dynamicContainer(input,
						Stream.of(DynamicContainer.dynamicContainer(
								input + " inner container",
								Stream.of(DynamicTest.dynamicTest(input + " Test 1", () -> {
									LOGGER.info("Checking length == 1");
									assertThat(input, hasLength(1));
								}), DynamicTest.dynamicTest(input + " test 2", () -> {
									LOGGER.info("Checking not empty");
									assertThat(input, not(emptyOrNullString()));
								}))
						))));
	}

	@Nested
	class Outer {
		@Nested
		class Inner {
			@Test
			void aTest() {
				LOGGER.info("executing aTest");
				Assertions.assertEquals(1, 1);
			}

			@Test
			void anotherTest() {
				LOGGER.info("executing anotherTest");
				Assertions.assertEquals(1, 1);
			}
		}
	}
}
