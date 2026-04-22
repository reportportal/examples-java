/*
 * Copyright 2026 EPAM Systems
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.epam.reportportal.example.testng.logback.step;

import com.epam.reportportal.annotations.Step;
import org.testng.annotations.Test;

/**
 * An example of a nested step in the inner abstract class.
 */
public class InnerAbstractNestedStepTest {

	private static abstract class AbstractInnerSteps {
		private String stepName;

		public AbstractInnerSteps(String name) {
			this.stepName = name;
		}

		@Step("Step name '{this.stepName}'")
		public void makeStep() {
			System.out.println("In the step: " + this.stepName);
		}
	}

	private static class InnerSteps extends AbstractInnerSteps {
		public InnerSteps(String name) {
			super(name);
		}
	}

	@Test
	public void innerNestedStepTest() {
		new InnerSteps("My abstract step name").makeStep();
	}
}
