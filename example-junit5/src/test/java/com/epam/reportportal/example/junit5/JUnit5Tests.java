package com.epam.reportportal.example.junit5;

import com.epam.reportportal.junit5.ReportPortalExtension;
import com.epam.reportportal.service.ReportPortal;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@ExtendWith(ReportPortalExtension.class)
class JUnit5Tests {

	@BeforeAll
	static void beforeAll() {
		System.out.println("before-all");
	}

	@BeforeEach
	void initializeBaseClass() {
		System.out.println("base-class-before-each");
	}

	@Test
	@Tag("tag1")
	@Tag("tag2")
	void baseClassTest() throws IOException {
		// Report launch log
		File file = File.createTempFile("rp-test", ".css");
		Resources.asByteSource(Resources.getResource("files/file.css")).copyTo(Files.asByteSink(file));
		ReportPortal.emitLaunchLog("LAUNCH LOG MESSAGE WITH ATTACHMENT", "error", new Date(), file);

		System.out.println("base-class-test");
	}

	@Disabled
	@Test
	void disabledTest() {
		System.out.println("disabled");
	}

	private static List<String> testData() {
		return Arrays.asList("first", "second", "third");
	}

	@TestFactory
	Stream<DynamicTest> testForTestFactory() {
		return testData().stream()
				.map(testData -> dynamicTest("Check Test Factory " + testData,
						() -> System.out.println("test-for-test-factory, test " + testData)
				));
	}

	@Nested
	class FirstContext {

		@BeforeEach
		void initializeFirstNesting() {
			System.out.println("nested-before-each");
		}

		@RepeatedTest(10)
		void firstNestedTest() {
			System.out.println("first-nested-test");
		}

		@Test
		void secondNestedTest() {
			System.out.println("second-nested-test");
		}

		@AfterEach
		void afterFirstContext() {
			System.out.println("nested-after-each");
		}

		@Nested
		class SecondContext {
			@BeforeEach
			void initializeSecondNesting() {
				System.out.println("nested-before-each");
			}

			@RepeatedTest(10)
			void firstNestedTest() {
				System.out.println("first-nested-test");
			}

			@Test
			void secondNestedTest() {
				System.out.println("second-nested-test");
			}

			@AfterEach
			void afterFirstContext() {
				System.out.println("nested-after-each");
			}
		}

	}
}
