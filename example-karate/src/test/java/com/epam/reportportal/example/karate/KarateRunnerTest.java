package com.epam.reportportal.example.karate;

import com.epam.reportportal.karate.ReportPortalHook;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KarateRunnerTest {

	@Test
	public void testAll() {
		Results results = Runner.builder()
				.path("classpath:features")
				.hook(new ReportPortalHook())
				.outputCucumberJson(true)
				.tags("~@ignore")
				.parallel(2);

		Assertions.assertEquals(0, results.getFailCount(), "Non-zero fail count.\n Errors:\n" + results.getErrorMessages());
	}
}
