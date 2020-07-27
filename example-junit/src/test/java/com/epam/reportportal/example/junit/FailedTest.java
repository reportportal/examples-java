package com.epam.reportportal.example.junit;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class FailedTest {

	@Test(expected = AssertionError.class)
	public void expectedFailureThrown() {
		Assert.assertEquals(2, 1);
	}

	@Test
	@Ignore
	public void testIgnore() {
		Assert.assertEquals(1, 1);
	}

	@Test
	public void expectToFail() {
		JUnitCore core = new JUnitCore();
		Result result = core.run(ExpectToFail.class);
		Assert.assertEquals(3, result.getFailureCount());
	}
}
