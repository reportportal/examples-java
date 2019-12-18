package com.epam.reportportal.example.testng.logback.step.pattern;

import java.util.List;

/**
 * @author <a href="mailto:ivan_budayeu@epam.com">Ivan Budayeu</a>
 */
public class Container {

	private List<Item> items;

	private String name;

	public Container(List<Item> items, String name) {
		this.items = items;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Container";
	}
}
