package com.epam.reportportal.example.junit5.annotated;

import com.epam.reportportal.annotations.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@DisplayName("Display name from DisplayName annotation on class level")
public class DisplayNameTest {
	@Test
	@DisplayName("Display name from DisplayName annotation on method level")
	public void testDisplayName() {
		System.out.println("Inside testDisplayName");
	}

	@TestFactory
	@DisplayName("Display name from DisplayName annotation on dynamic test factory")
	public Stream<DynamicTest> testForTestFactory() {
		return Stream.of(dynamicTest("My dynamic test", () -> System.out.println("Inside dynamic test")));
	}

	@Test
	@org.junit.jupiter.api.DisplayName("Display name from JUnit's annotation on method level")
	@DisplayName("Display name from ReportPortal's annotation overrides JUnit's annotation")
	public void testDisplayNameOverride() {
		System.out.println("Inside testDisplayNameOverride");
	}
}
