package com.epam.reportportal.example.junit5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.DayOfWeek;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParametrizedTest {

	@ParameterizedTest
	@ValueSource(strings = { "message1", "message2" })
	void testNotBlankParams(String input) {
		assertFalse(Checks.isBlank(input));
	}

	@ParameterizedTest
	@ValueSource(strings = { " ", "   ", "" })
	void testBlankParams(String input) {
		assertTrue(Checks.isBlank(input));
	}

	@ParameterizedTest
	@ValueSource(ints = { 1, 3, 5 })
	void testOddIntegers(Integer input) {
		assertTrue(Checks.isOdd(input));
	}

	@ParameterizedTest
	@ValueSource(ints = { 2, 4, 6 })
	void testEvenIntegers(Integer input) {
		assertFalse(Checks.isOdd(input));
	}

	private static Iterable<Object[]> testData() {
		return Collections.singleton(new Object[]{"message1"});
	}

	@ParameterizedTest
	@MethodSource("testData")
	void parameterizedTestWithMethodSource(String value) {
		System.out.println("parameterized-test-with-method-source, parameter: " + value);
	}

	@ParameterizedTest
	@CsvSource({ "first", "second", "third" })
	void parameterizedTestWithCsvSource(String value) {
		System.out.println("parameterized-test-with-csv-source, parameter: " + value);
	}

	@ParameterizedTest
	@EnumSource(value = DayOfWeek.class, names = { "MONDAY", "WEDNESDAY", "FRIDAY" })
	void parameterizedTestWithEnumSource(DayOfWeek day) {
		System.out.println("parameterized-test-with-enum-source, parameter: " + day);
	}

	@ParameterizedTest
	@NullSource
	void parameterizedTestWithNullSource(String value) {
		System.out.println("parameterized-test-with-null-source, parameter: " + value);
	}

	@ParameterizedTest
	@NullSource
	@ValueSource(strings = { "a", "b", "c" })
	void parameterizedTestWithNullSourceAndValueSource(String value) {
		System.out.println("parameterized-test-with-null-source-and-value-source, parameter: " + value);
	}

	@ParameterizedTest
	@EmptySource
	void parameterizedTestWithEmptySource(String value) {
		System.out.println("parameterized-test-with-empty-source, parameter: " + value);
	}

	@ParameterizedTest
	@EmptySource
	@ValueSource(strings = { "a", "b", "c" })
	void parameterizedTestWithEmptySourceAndValueSource(String value) {
		System.out.println("parameterized-test-with-empty-source-and-value-source, parameter: " + value);
	}

	@ParameterizedTest
	@NullSource
	@EmptySource
	@ValueSource(strings = { "a", "b", "c" })
	void parameterizedTestWithNullSourceAndEmptySourceAndValueSource(String value) {
		System.out.println("parameterized-test-with-null-source-empty-source-and-value-source, parameter: " + value);
	}

	@ParameterizedTest
	@NullAndEmptySource
	void parameterizedTestWithNullAndEmptySource(String value) {
		System.out.println("parameterized-test-with-null-and-empty-source, parameter: " + value);
	}

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = { "a", "b", "c" })
	void parameterizedTestWithNullAndEmptySourceAndValueSource(String value) {
		System.out.println("parameterized-test-with-null-and-empty-source-and-value-source, parameter: " + value);
	}

	@ParameterizedTest
	@ValueSource(floats = { 1.1f, 2.2f, 3.3f })
	void parameterizedTestWithFloatsValueSource(float value) {
		System.out.println("parameterized-test-with-value-source, parameter: " + value);
	}

	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 3 })
	void parameterizedTestWithIntValueSource(int value) {
		System.out.println("parameterized-test-with-value-source, parameter: " + value);
	}

	@ParameterizedTest
	@ValueSource(shorts = { 1, 2, 3 })
	void parameterizedTestWithShortsValueSource(short value) {
		System.out.println("parameterized-test-with-value-source, parameter: " + value);
	}

	@ParameterizedTest
	@ValueSource(bytes = { 1, 2, 3 })
	void parameterizedTestWithBytesValueSource(byte value) {
		System.out.println("parameterized-test-with-value-source, parameter: " + value);
	}

	@ParameterizedTest
	@ValueSource(chars = { 'a', 'b', 'c' })
	void parameterizedTestWithCharsValueSource(char value) {
		System.out.println("parameterized-test-with-value-source, parameter: " + value);
	}

	@ParameterizedTest
	@ValueSource(doubles = { 1.1, 2.2, 3.3 })
	void parameterizedTestWithDoublesValueSource(double value) {
		System.out.println("parameterized-test-with-value-source, parameter: " + value);
	}

	@ParameterizedTest
	@ValueSource(longs = { 1147483648L, 2147483648L, 3147483648L })
	void parameterizedTestWithLongsValueSource(long value) {
		System.out.println("parameterized-test-with-value-source, parameter: " + value);
	}

	@ParameterizedTest
	@ValueSource(strings = { "a", "b", "c" })
	void parameterizedTestWithStringsValueSource(String value) {
		System.out.println("parameterized-test-with-value-source, parameter: " + value);
	}

	private static class Checks {
		static boolean isBlank(String input) {
			return null == input || input.trim().length() == 0;
		}

		static boolean isOdd(Integer input) {
			return input % 2 != 0;
		}
	}
}
