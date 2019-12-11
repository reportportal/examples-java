package com.epam.reportportal.example.testng.log4j.parametrized;

import com.epam.reportportal.annotations.ParameterKey;
import com.epam.reportportal.annotations.UniqueID;
import com.epam.reportportal.example.testng.log4j.extension.ParameterizedTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * Parametrized test. You should get all parameters. Custom keys for parameters
 * Custom uniqueID.
 *
 * @author Pavel Bortnik
 */
public class ParametrizedTest {

	private static final Logger LOGGER = LogManager.getLogger(ParameterizedTest.class);

	@Test
	@Parameters({ "message" })
	@UniqueID("HOOOOAA-my-very-unique-id")
	public void testParams(String msg) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			LOGGER.info(msg + ": " + i);
			if (i == 1) {
				Thread.sleep(TimeUnit.SECONDS.toMillis(5L));
			}
		}
	}

	@Test(threadPoolSize = 2, dataProvider = "bla-bla")
	public void testParams(@ParameterKey("my_great_parameter") String msg, String msg2) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			LOGGER.info(msg + ": " + msg2);
			if (i == 1) {
				Thread.sleep(TimeUnit.SECONDS.toMillis(5L));
			}
		}
	}

	@DataProvider(parallel = true, name = "bla-bla")
	public Iterator<Object[]> params() {
		return Arrays.asList(new Object[] { "one", "two" }, new Object[] { "two", "one" }, new Object[] { "three", null }).iterator();
	}

}
