package com.epam.reportportal.example.testng.logback.extension;

import com.epam.reportportal.testng.BaseTestNGListener;
import com.epam.reportportal.testng.TestNGService;
import com.epam.ta.reportportal.ws.model.FinishTestItemRQ;
import com.epam.ta.reportportal.ws.model.StartTestItemRQ;
import com.epam.ta.reportportal.ws.model.attribute.ItemAttributesRQ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import rp.com.google.common.base.Optional;
import rp.com.google.common.base.Throwables;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Extension example
 *
 * @author Andrei Varabyeu
 */
@Listeners({ ParameterizedTest.ExtendedListener.class })
public class ParameterizedTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParameterizedTest.class);

	@Test(threadPoolSize = 2, dataProvider = "bla-bla")
	public void testParams(String msg) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			LOGGER.info(msg + ": " + i);
			if (i == 1) {
				Thread.sleep(TimeUnit.SECONDS.toMillis(5L));
			}
		}
	}

	@DataProvider(parallel = true, name = "bla-bla")
	public Iterator<Object[]> params() {
		return Arrays.asList(new Object[] { "one" }, new Object[] { "two" }).iterator();
	}

	public static class ExtendedListener extends BaseTestNGListener {
		public ExtendedListener() {
			super(new ParamTaggingTestNgService());
		}

		@Override
		public void onTestStart(ITestResult testResult) {
			System.out.println(testResult.getMethod().getInvocationCount());

			System.out.println(testResult.getMethod().getCurrentInvocationCount());
			super.onTestStart(testResult);
		}
	}

	public static class ParamTaggingTestNgService extends TestNGService {

		@Override
		protected StartTestItemRQ buildStartStepRq(ITestResult testResult) {
			final StartTestItemRQ rq = super.buildStartStepRq(testResult);
			if (testResult.getParameters() != null && testResult.getParameters().length != 0) {
				Set<ItemAttributesRQ> attributes = Optional.fromNullable(rq.getAttributes()).or(new HashSet<>());
				for (Object param : testResult.getParameters()) {
					attributes.add(new ItemAttributesRQ(null, param.toString()));
				}
				rq.setAttributes(attributes);

			}
			return rq;
		}

		@Override
		protected FinishTestItemRQ buildFinishTestMethodRq(String status, ITestResult testResult) {
			FinishTestItemRQ finishTestItemRQ = super.buildFinishTestMethodRq(status, testResult);
			if (testResult.getThrowable() != null) {
				String description = "```error\n" + Throwables.getStackTraceAsString(testResult.getThrowable()) + "\n```";
				description = description + Throwables.getStackTraceAsString(testResult.getThrowable());
				finishTestItemRQ.setDescription(description);
			}
			return finishTestItemRQ;
		}
	}

}
