package com.epam.reportportal.example.karate;

import com.epam.reportportal.karate.KarateReportPortalRunner;
import com.intuit.karate.Results;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KarateRunnerTest {

	@Test
	public void testAll() {
		Results results = KarateReportPortalRunner // Our runner with a Karate Hook
				.path("classpath:features") // Path with feature files
				.outputCucumberJson(true) // Generate cucumber report
				.tags("~@ignore") // Ignore tests marked with the tag
				.parallel(2); // Run in 2 Threads
		Assertions.assertEquals(0, results.getFailCount(), "Non-zero fail count.\n Errors:\n" + results.getErrorMessages());
	}
}
