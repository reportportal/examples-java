package com.epam.reportportal.example.cucumber;

import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.TestCaseIdKey;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class Stepdefs {
	
	private Belly belly = new Belly();

	@TestCaseId(parametrized = true)
    @Given("^I have (\\d+) cukes in my belly$")
    public void I_have_cukes_in_my_belly(@TestCaseIdKey int cukes) {
        belly.eat(cukes);
    }

    @TestCaseId(parametrized = true)
    @When("^I wait (\\d+) hour$")
    public void I_wait(int hours) {
    	belly.wait(hours);
    }

    @TestCaseId(value = "C1.1.0")
    @Then("^my belly should growl$")
    public void my_belly_should_growl() {
    	assertTrue(belly.growl());
    }
}
