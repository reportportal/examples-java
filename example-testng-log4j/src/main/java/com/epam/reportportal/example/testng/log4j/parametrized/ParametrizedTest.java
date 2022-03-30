package com.epam.reportportal.example.testng.log4j.parametrized;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A test to verify this issue: https://github.com/reportportal/reportportal/issues/789
 * <p>
 * Expected results: tests should not join into retry chain.
 */
public class ParametrizedTest {

	private static final Logger LOGGER = LogManager.getLogger(ParametrizedTest.class);

	private final Hero hero;

	@DataProvider(name = "provider")
	public static Object[][] getData() {
		return commonReadParametersFromYaml("files/yaml.yml");
	}

	@Factory(dataProvider = "provider")
	public ParametrizedTest(String name, String power, String race) {
		hero = new Hero(name, power, race);
	}

	@Test()
	public void someChecks() {
		SoftAssert softAssert = new SoftAssert();
		// some actions
		LOGGER.info(hero);
		softAssert.assertNull(hero);
		softAssert.assertAll();
	}

	static Object[][] commonReadParametersFromYaml(String fileName) {
		LOGGER.traceEntry();

		LOGGER.info("File name: {}", fileName);

		Object[][] data = null;

		try {
			Yaml yaml = new Yaml();
			ClassLoader classLoader = ClassLoader.getSystemClassLoader();
			File file = new File(classLoader.getResource(fileName).getFile());
			FileInputStream ios = new FileInputStream(file);

			ArrayList<Map<String, String>> list = yaml.loadAs(ios, ArrayList.class);

			data = new Object[list.size()][];
			for (int i = 0; i < list.size(); i++) {

				List<String> values = new ArrayList<>(list.get(i).values());
				int valuesSize = values.size();

				data[i] = new Object[valuesSize];
				for (int j = 0; j < valuesSize; j++) {
					data[i][j] = values.get(j);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return LOGGER.traceExit(data);
	}

	private static class Hero {
		private String name;
		private String power;
		private String race;

		public Hero(String name, String power, String race) {
			this.name = name;
			this.power = power;
			this.race = race;
		}

		public String getName() {
			return name;
		}

		public String getPower() {
			return power;
		}

		public String getRace() {
			return race;
		}

		@Override
		public String toString() {
			return "Hero{" + "name='" + name + '\'' + ", power='" + power + '\'' + ", race='" + race + '\'' + '}';
		}
	}

}
