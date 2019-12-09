package com.epam.reportportal.example.testng.logback.bugs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.ITestAnnotation;
import org.testng.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

//@Listeners({ReproduceItemIdTest.RetryAnnotationListener.class, ReportPortalTestNGListener.class })
public class ReproduceItemIdTest {
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@BeforeClass
	public void bcReproduceItemIdTest() {
		logger.info("bcReproduceItemIdTest");
		Assert.assertTrue(false); // requirement: fail at non @Test method
	}

//	    @BeforeMethod
//	    public void bmReproduceItemIdTest() {
//	        logger.info("bmReproduceItemIdTest");
//	        Assert.assertTrue(false); // requirement: fail at non @Test method
//	    }

	@Test
	public void test0() {
		logger.info("test0");
		Assert.assertTrue(true);
	}

	public static class RetryAnalyzer implements IRetryAnalyzer {
		private final Logger logger = LoggerFactory.getLogger(this.getClass());

		int counter = 0;
		int retryLimit = 2;

		@Override
		public boolean retry(ITestResult result) {
			if (result.isSuccess()) {
				return false;
			}
			if (counter < retryLimit) {
				if (result.getThrowable() != null) {
					logger.warn(
							String.format("Test %s failed with exception. Will retry... (%s attempt)", result.getName(), counter),
							result.getThrowable()
					);
				}
				counter++;
				return true;
			}
			return false;
		}
	}

	/**
	 * This class is a Listener. It will apply retry mechanism (RetryAnalyzer class) for every test method which doesn't have RetryAnalyzer specified at annotation
	 */
	public static class RetryAnnotationListener implements IAnnotationTransformer {

		@Override
		public void transform(ITestAnnotation testAnnotation, Class testClass, Constructor testConstructor, Method testMethod) {
			Class<? extends IRetryAnalyzer> retry = testAnnotation.getRetryAnalyzerClass();

			if (retry == null) {
				testAnnotation.setRetryAnalyzer(RetryAnalyzer.class);
			}
		}
	}
}
