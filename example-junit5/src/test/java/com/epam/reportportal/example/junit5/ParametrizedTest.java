package com.epam.reportportal.example.junit5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

	private static class Checks {
		static boolean isBlank(String input) {
			return null == input || input.trim().length() == 0;
		}

		static boolean isOdd(Integer input) {
			return input % 2 != 0;
		}
	}
}
