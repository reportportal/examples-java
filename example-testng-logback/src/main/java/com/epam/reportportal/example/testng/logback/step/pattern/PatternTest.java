package com.epam.reportportal.example.testng.logback.step.pattern;

import com.epam.reportportal.annotations.Step;
import com.epam.reportportal.annotations.StepTemplateConfig;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:ivan_budayeu@epam.com">Ivan Budayeu</a>
 */
@Listeners(ReportPortalTestNGListener.class)
public class PatternTest {

	@Test
	public void containerTest() {
		Item item = new Item("ball");
		List<Item> items = new ArrayList<>();
		items.add(item);
		Container container = new Container(items, "box");
		checkContainerWithSimplePattern(container);
		checkContainerWithNamePattern(container);
		checkContainerWithCollectionPattern(container);
		checkMethod("Bubble sorting");
	}

	@Step("Check container - {container}")
	private void checkContainerWithSimplePattern(Container container) {

	}

	@Step("Check container - {container.name}")
	private void checkContainerWithNamePattern(Container container) {

	}

	@Step("Check container - {container.items.name}")
	private void checkContainerWithCollectionPattern(Container container) {

	}

	@Step(value = "My {method} explanation using test method - {m}", templateConfig = @StepTemplateConfig(methodNameTemplate = "m"))
	private void checkMethod(String method) {

	}

}
