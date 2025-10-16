package com.epam.reportportal.example.karate;

import com.epam.reportportal.karate.KarateReportPortalRunner;
import com.epam.reportportal.karate.ReportPortalHook;
import com.intuit.karate.Results;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KarateRunnerTest {

	@Test
	public void testAll() {
		ReportPortalHook hook = new ReportPortalHook();
		Results results = KarateReportPortalRunner.path("classpath:features")
				.hook(hook)
				.outputCucumberJson(true)
				.tags("~@ignore")
				.parallel(2);
		Assertions.assertEquals(0, results.getFailCount(), "Non-zero fail count.\n Errors:\n" + results.getErrorMessages());
	}
}
