/*
 * Copyright 2021 EPAM Systems
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.epam.reportportal.example.cucumber6.attributes;

import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import com.epam.reportportal.annotations.attribute.MultiKeyAttribute;
import com.epam.reportportal.annotations.attribute.MultiValueAttribute;
import com.epam.reportportal.example.cucumber6.Belly;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

public class Stepdefs {

	private final Belly belly = new Belly();

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
