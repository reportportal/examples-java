package com.epam.reportportal.example.junit5.annotated;

import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.TestCaseIdKey;
import com.epam.reportportal.junit5.ReportPortalExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@ExtendWith(ReportPortalExtension.class)
class JUnit5Tests {

	@TestCaseId(value = "JU5.1.0")
	@Test
	void baseClassTest() {

	}

	@TestCaseId(parametrized = true)
	@ParameterizedTest
	@CsvSource({ "first", "second", "third" })
	void parameterizedTestWithCsvSource(@TestCaseIdKey String value) {

	}
}
