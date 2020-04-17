package com.epam.reportportal.example.junit.extension;

import com.nordstrom.automation.junit.JUnitRetryAnalyzer;
import org.junit.runners.model.FrameworkMethod;

public class JUnitRetryImpl implements JUnitRetryAnalyzer {

	@Override
	public boolean retry(FrameworkMethod method, Throwable thrown) {
		return true;
	}
}
