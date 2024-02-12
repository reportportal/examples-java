package com.epam.reportportal.example.junit5;

import com.epam.reportportal.example.junit5.util.AttachmentHelper;
import com.epam.reportportal.junit5.ReportPortalExtension;
import com.epam.reportportal.service.ReportPortal;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;
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
	void baseClassTest() {
		// Report launch log
		File file = AttachmentHelper.getFileFromResources("src/test/resources/files", "file", "css");
		ReportPortal.emitLaunchLog("LAUNCH LOG MESSAGE WITH ATTACHMENT", "error", new Date(), file);
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
