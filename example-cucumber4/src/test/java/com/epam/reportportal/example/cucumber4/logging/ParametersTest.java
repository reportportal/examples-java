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

package com.epam.reportportal.example.cucumber4.logging;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:ihar_kahadouski@epam.com">Ihar Kahadouski</a>
 */
public class ParametersTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParametersTest.class);
	private int itemsCount;

	@Given("I have {int} {string} in my pocket")
	public void iHaveNumberItemInMyPocket(int number, String item) {
		itemsCount = number;
		LOGGER.info("I have {} {} in my pocket", number, item);

	}

	@When("^I eat one$")
	public void iEatOne() {
		itemsCount -= 1;
		LOGGER.info("I eat one");
	}

	@Then("I have {int} in my pocket")
	public void iHaveResultInMyPocket(int result) {
		assertEquals(result, itemsCount);
		LOGGER.info("I have {} in my pocket", result);
	}
}
