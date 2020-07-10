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

package com.epam.reportportal.example.junit5.nested.step.pattern;

import com.epam.reportportal.annotations.Step;
import com.epam.reportportal.annotations.StepTemplateConfig;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:ivan_budayeu@epam.com">Ivan Budayeu</a>
 */
public class PatternTest {

	@Test
	public void containerTest() {
		Item item = new Item("ball");
		List<Item> items = new ArrayList<>();
		items.add(item);
		Container container = new Container(items, "box");
		checkContainerWithSimplePattern(container);
		checkContainerWithNamePattern(container);
		checkContainerWithCollectionPattern(container);
		checkMethod("Bubble sorting");
	}

	@Step("Check container - {container}")
	private void checkContainerWithSimplePattern(Container container) {

	}

	@Step("Check container - {container.name}")
	private void checkContainerWithNamePattern(Container container) {

	}

	@Step("Check container - {container.items.name}")
	private void checkContainerWithCollectionPattern(Container container) {

	}

	@Step(value = "My {method} explanation using test method - {m}", templateConfig = @StepTemplateConfig(methodNameTemplate = "m"))
	private void checkMethod(String method) {

	}

}
