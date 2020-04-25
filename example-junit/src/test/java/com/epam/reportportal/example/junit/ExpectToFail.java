package com.epam.reportportal.example.junit;

import org.junit.Assert;
import org.junit.Test;

public class ExpectToFail {

	@Test
	public void testFailure() {
		Assert.assertEquals(2, 1);
	}

	@Test
	public void testFailureWithCustomMessage() {
		Assert.assertEquals("Failure msg", 2, 1);
	}

	@Test(expected = AssertionError.class)
	public void expectedFailureAbsent() {
		Assert.assertEquals(1, 1);
	}

}
