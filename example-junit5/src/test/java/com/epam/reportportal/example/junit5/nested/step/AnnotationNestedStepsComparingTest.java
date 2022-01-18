/*
 * Copyright 2020 EPAM Systems
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

package com.epam.reportportal.example.junit5.nested.step;

import com.epam.reportportal.annotations.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:ivan_budayeu@epam.com">Ivan Budayeu</a>
 */
public class AnnotationNestedStepsComparingTest {

	public static final Logger LOGGER = LoggerFactory.getLogger(AnnotationNestedStepsComparingTest.class);

	@Test
	void orderProductsTest() {

		final Integer productCount = 5;
		final Double price = 3.0;
		final Double totalPrice = price * productCount;

		navigateToMainPage();
		login();
		navigateToProductsPage();
		addProductToCart(productCount);
		pay(totalPrice);
		logout();
	}

	@Step
	public void navigateToMainPage() {
		LOGGER.info("Main page displayed");
	}

	@Step
	public void login() {
		OrderingSimulator.logIn();
		LOGGER.info("User logged in");
	}

	@Step
	public void navigateToProductsPage() {
		List<String> products = OrderingSimulator.getProducts();
		LOGGER.info("Products page opened");
	}

	@Step("Add {count} products to the cart")
	public void addProductToCart(Integer count) {
		String product = clickOnProduct();
		selectProductsCount(count);
		clickCartButton(product, count);
	}

	@Step
	private String clickOnProduct() {
		LOGGER.info("Product click event");
		return OrderingSimulator.chooseProduct();
	}

	@Step("{method} with {count} products")
	private void selectProductsCount(Integer count) {
		LOGGER.info(count + " products selected");
	}

	@Step("{productCount} products added")
	private void clickCartButton(String product, Integer productCount) {
		OrderingSimulator.addProduct(product, productCount);
		LOGGER.info(productCount + " products added to the cart");
		Assertions.assertEquals(5, productCount.intValue());
	}

	@Step("Payment step with price = {totalPrice}")
	public void pay(Double totalPrice) {
		OrderingSimulator.doPayment(totalPrice);
		LOGGER.info("Successful payment");
	}

	@Step
	public void logout() {
		OrderingSimulator.logOut();
		LOGGER.info("User logged out");
	}

	@Test
	void orderProductsTestOld() {

		final Integer productCount = 5;
		final Double price = 3.0;
		final Double totalPrice = price * productCount;

		LOGGER.info("Main page displayed");

		OrderingSimulator.logIn();
		LOGGER.info("User logged in");

		List<String> products = OrderingSimulator.getProducts();
		LOGGER.info("Products page opened");

		String product = OrderingSimulator.chooseProduct();
		LOGGER.info("Product click event");

		LOGGER.info(productCount + " products selected");

		OrderingSimulator.addProduct(product, productCount);
		LOGGER.info(productCount + " products added to the cart");
		Assertions.assertEquals(5, productCount.intValue());

		OrderingSimulator.doPayment(totalPrice);
		LOGGER.info("Successful payment");

		OrderingSimulator.logOut();
		LOGGER.info("User logged out");
	}

	private static class OrderingSimulator {

		public static void logIn() {
			initChromeDriver();
		}

		private static void initChromeDriver() {

		}

		public static List<String> getProducts() {
			return new ArrayList<>();
		}

		public static String chooseProduct() {
			return "productName";
		}

		public static void addProduct(String name, Integer count) {

		}

		public static void doPayment(Double price) {

		}

		public static void logOut() {

		}
	}
}
