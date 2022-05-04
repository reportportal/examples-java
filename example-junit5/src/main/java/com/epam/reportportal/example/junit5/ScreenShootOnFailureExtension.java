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

package com.epam.reportportal.example.junit5;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import org.openqa.selenium.OutputType;

import java.lang.reflect.Method;

/**
 * JUnit 5the extension which makes a Selenide screenshot in case of test failure.
 */
public class ScreenShootOnFailureExtension implements InvocationInterceptor {

	/**
	 * Make a screenshot with Selenide and attach it to the current test item on Report Portal in case of test failure.
	 *
	 * @param invocation        JUnit 5th invocation object
	 * @param invocationContext JUnit 5th invocation context
	 * @param extensionContext  JUnit 5th extension context
	 */
	@Override
	public void interceptTestMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext,
			ExtensionContext extensionContext) throws Throwable {
		try {
			invocation.proceed();
		} catch (Throwable cause) {
			byte[] screenshot = Selenide.screenshot(OutputType.BYTES);
			LoggingUtils.log(screenshot, "Failure screenshot");
			throw cause;
		}
	}
}
