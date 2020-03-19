package com.epam.reportportal.example.cucumber.logging;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:ihar_kahadouski@epam.com">Ihar Kahadouski</a>
 */
public class DataTableTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataTableTest.class);

	private List<String> bag = new ArrayList<>();

	private int found;

	@Given("^I have the following items in bag$")
	public void iHaveTheFollowingItemsInBag(DataTable dataTable) {
		bag = dataTable.asList(String.class);
		LOGGER.info("I have the following items in bag {}", bag);
	}

	@When("^I search for (.*)$")
	public void iSearchForGlasses(String item) {
		LOGGER.info("I'm searching for {}", item);
		found = bag.contains(item) ? 1 : 0;
	}

	@Then("^I find (\\d+) items$")
	public void iFindItems(int expected) {
		LOGGER.info("I found {} items", expected);
		assertEquals(expected, found);
	}
}
