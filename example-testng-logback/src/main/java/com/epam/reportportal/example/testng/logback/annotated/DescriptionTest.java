package com.epam.reportportal.example.testng.logback.annotated;

import com.epam.reportportal.annotations.Description;
import org.testng.annotations.Test;

public class DescriptionTest {

	@Test(description = "Description from Test annotation")
	public void testDescription() {
		System.out.println("In testDescriptionTest");
	}

	@Test
	@Description("Description from Description annotation")
	public void testDescriptionAnnotation() {
		System.out.println("In testDescriptionAnnotation");
	}

	@Test(description = "Description from Test annotation should be ignored")
	@Description("Description from Description annotation should override description from Test annotation")
	public void testDescriptionAnnotationOverridesTestAnnotation() {
		System.out.println("In testDescriptionAnnotationOverridesTestAnnotation");
	}
}
