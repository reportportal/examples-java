package com.epam.reportportal.example.cucumber2.attribute;

import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import com.epam.reportportal.annotations.attribute.MultiKeyAttribute;
import com.epam.reportportal.annotations.attribute.MultiValueAttribute;
import com.epam.reportportal.example.cucumber2.Belly;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class Stepdefs {

	private Belly belly = new Belly();

	@Attributes(attributes = { @Attribute(key = "key", value = "value") })
	@Given("^I have (\\d+) cukes in my belly$")
	public void I_have_cukes_in_my_belly(int cukes) {
		belly.eat(cukes);
	}

	@Attributes(attributes = { @Attribute(key = "key1", value = "value1"),
			@Attribute(key = "key2", value = "value2") }, multiKeyAttributes = { @MultiKeyAttribute(keys = { "k1", "k2" }, value = "v") })
	@When("^I wait (\\d+) hour$")
	public void I_wait(int hours) {
		belly.wait(hours);
	}

	@Attributes(multiValueAttributes = { @MultiValueAttribute(isNullKey = true, values = { "v1", "v2" }) })
	@Then("^my belly should growl$")
	public void my_belly_should_growl() {
		assertTrue(belly.growl());
	}
}
