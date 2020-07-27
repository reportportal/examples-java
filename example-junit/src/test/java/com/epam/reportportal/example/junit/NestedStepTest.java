package com.epam.reportportal.example.junit;

import com.epam.reportportal.annotations.Step;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:ivan_budayeu@epam.com">Ivan Budayeu</a>
 */
public class NestedStepTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(NestedStepTest.class);

	private static final String METHODS_NAME_TEMPLATE = "My name is - `{method}` and I am a nested step of the `{parent}`";

	private static ThreadLocal<Integer> counter = new InheritableThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return 0;
		}
	};

	@Before
	public void beforeEach() {
		first();
	}

	@Test
	public void firstTest() {
		LOGGER.error("yeah");
		greenNestedStep("firstTest");
	}

	@Test
	public void secondTest() {
		redNestedStep("secondTest");
	}

	@Step(value = METHODS_NAME_TEMPLATE)
	public void greenNestedStep(String parent) {
		LOGGER.error("hello");
		if (counter.get() == 5) {
			return;
		}
		counter.set(counter.get() + 1);
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
