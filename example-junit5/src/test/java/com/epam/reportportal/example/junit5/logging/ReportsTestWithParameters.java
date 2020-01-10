package com.epam.reportportal.example.junit5.logging;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;

public class ReportsTestWithParameters {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportAttachmentsTest.class);

    @BeforeEach
    void beforeEach() {
        LOGGER.info("base-class-before-each");
    }

    @AfterEach
    void afterEach() {
        LOGGER.info("base-class-after-each");
    }

    private static List<String> testData() {
        return Arrays.asList("first", "second", "third");
    }

    @ParameterizedTest
    @MethodSource("testData")
    void parameterizedTestWithMethodSource(String value) {
        LOGGER.info("parameterized-test-with-method-source, parameter: " + value);
    }

    @ParameterizedTest
    @CsvSource({"first", "second", "third"})
    void parameterizedTestWithCsvSource(String value) {
        LOGGER.info("parameterized-test-with-csv-source, parameter: " + value);
    }

    @ParameterizedTest
    @EnumSource(value = DayOfWeek.class, names = {"MONDAY", "WEDNESDAY", "FRIDAY"})
    void parameterizedTestWithEnumSource(DayOfWeek day) {
        LOGGER.info("parameterized-test-with-enum-source, parameter: " + day);
    }

    @ParameterizedTest
    @NullSource
    void parameterizedTestWithNullSource(String value) {
        LOGGER.info("parameterized-test-with-null-source, parameter: " + value);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"a", "b", "c"})
    void parameterizedTestWithNullSourceAndValueSource(String value) {
        LOGGER.info("parameterized-test-with-null-source-and-value-source, parameter: " + value);
    }

    @ParameterizedTest
    @EmptySource
    void parameterizedTestWithEmptySource(String value) {
        LOGGER.info("parameterized-test-with-empty-source, parameter: " + value);
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {"a", "b", "c"})
    void parameterizedTestWithEmptySourceAndValueSource(String value) {
        LOGGER.info("parameterized-test-with-empty-source-and-value-source, parameter: " + value);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {"a", "b", "c"})
    void parameterizedTestWithNullSourceAndEmptySourceAndValueSource(String value) {
        LOGGER.info("parameterized-test-with-null-source-empty-source-and-value-source, parameter: " + value);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void parameterizedTestWithNullAndEmptySource(String value) {
        LOGGER.info("parameterized-test-with-null-and-empty-source, parameter: " + value);
    }
}
