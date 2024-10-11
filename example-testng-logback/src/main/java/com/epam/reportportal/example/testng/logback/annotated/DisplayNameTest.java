package com.epam.reportportal.example.testng.logback.annotated;

import com.epam.reportportal.annotations.DisplayName;
import org.testng.annotations.Test;

@Test(testName = "Name from Test annotation")
public class DisplayNameTest {
	@Test
	public void testDisplayNameFromTest() {
		System.out.println("The Test annotation on class level should add name to the test");
	}

	@Test
	@DisplayName("Name from DisplayName annotation")
	public void testDisplayNameOverride() {
		System.out.println("The DisplayName annotation should override name from Test annotation");
	}
}
