package com.epam.reportportal.example.junit5.annotated;

import com.epam.reportportal.annotations.Description;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@Description("Description from Description annotation on class level")
public class DescriptionTest {

	@Test
	@Description("Description from Description annotation on method level overrides Description set at class level")
	public void testDescriptionTestMethod() {
		System.out.println("Inside testDescriptionTestMethod");
	}

	@Test
	public void testDescriptionTestClass() {
		System.out.println("Inside testDescriptionTestClass");
	}

	@TestFactory
	@Description("Description from Description annotation on dynamic test factory")
	public Stream<DynamicTest> testForTestFactory() {
		return Stream.of(dynamicTest("My dynamic test", () -> System.out.println("Inside dynamic test")));
	}
}
