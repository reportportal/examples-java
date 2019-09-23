package com.epam.reportportal.example.junit;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SimpleTest {
	
	@Test
	public void passingTest() {
		assertTrue("This test shouldn't fail", true);
	}
	
}
