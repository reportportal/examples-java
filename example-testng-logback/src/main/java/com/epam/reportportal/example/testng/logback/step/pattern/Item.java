package com.epam.reportportal.example.testng.logback.step.pattern;

/**
 * @author <a href="mailto:ivan_budayeu@epam.com">Ivan Budayeu</a>
 */
public class Item {
	private String name;

	public Item(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Item";
	}
}
