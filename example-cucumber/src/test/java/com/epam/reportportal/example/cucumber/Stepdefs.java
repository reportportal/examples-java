package com.epam.reportportal.example.cucumber;

import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.TestCaseIdKey;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class Stepdefs {
	
	private Belly belly = new Belly();

	@TestCaseId(isParameterized = true)
    @Given("^I have (\\d+) cukes in my belly$")
    public void I_have_cukes_in_my_belly(@TestCaseIdKey(isInteger = true) int cukes) {
        belly.eat(cukes);
    }

    @TestCaseId(isParameterized = true)
    @When("^I wait (\\d+) hour$")
    public void I_wait(int hours) {
    	belly.wait(hours);
    }

    @TestCaseId(value = 111)
    @Then("^my belly should growl$")
    public void my_belly_should_growl() {
    	assertTrue(belly.growl());
    }
}
