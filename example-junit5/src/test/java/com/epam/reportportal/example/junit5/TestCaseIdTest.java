package com.epam.reportportal.example.junit5;

import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.TestCaseIdKey;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class TestCaseIdTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestCaseIdTest.class);

	@Test
	void testCaseIdFromCodeRefTest() {
		LOGGER.info("Test case id should be generated from code reference");
	}

	@ParameterizedTest
	@ValueSource(ints = { 101, 0, 1 })
	void testCaseIdFromCodeRefAndParamsTest(int parameter) {
		LOGGER.info("Test case id should be generated from code reference and parameters");
		LOGGER.info("Parameter {}", parameter);
	}

	@TestCaseId("test-case-id")
	@Test
	void testCaseIdFromAnnotationValueTest() {
		LOGGER.info("Test case id should be provided from annotation value");
	}

	@TestCaseId(parametrized = true)
	@ParameterizedTest
	@ValueSource(strings = { "one", "two" })
	void testCaseIdFromParametrizedAnnotationTest(@TestCaseIdKey String parameter) {
		LOGGER.info("Test case id should be generated from parameter value marked @TestCaseIdKey annotation");
		LOGGER.info("Parameter {}", parameter);
	}

	@TestCaseId(parametrized = true)
	@ParameterizedTest
	@ValueSource(chars = { 't', 'e', 's', 't' })
	void testCaseIdFromParametrizedAnnotationTest(@TestCaseIdKey char parameter) {
		LOGGER.info("Test case id should be generated from parameter value marked @TestCaseIdKey annotation");
		LOGGER.info("Parameter {}", parameter);
	}

	@TestFactory
	Stream<DynamicTest> dynamicTests() {
		return Stream.of(dynamicTest("testCaseIdFromDynamicTest",
				() -> LOGGER.info("Test case id should be generated from code reference")
		));
	}

	@TestCaseId("test-case-id-dynamic")
	@TestFactory
	Stream<DynamicTest> dynamicAnnotatedTests() {
		return Stream.of(dynamicTest("testCaseIdFromDynamicTest",
				() -> LOGGER.info("Test case id should be generated from annotation value")
		));
	}
}
