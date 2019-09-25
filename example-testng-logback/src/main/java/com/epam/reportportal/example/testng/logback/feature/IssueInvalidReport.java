package com.epam.reportportal.example.testng.logback.feature;

import com.epam.reportportal.listeners.ListenerParameters;
import com.epam.reportportal.service.Launch;
import com.epam.reportportal.service.ReportPortal;
import com.epam.reportportal.testng.BaseTestNGListener;
import com.epam.reportportal.testng.TestNGService;
import com.epam.ta.reportportal.ws.model.FinishTestItemRQ;
import com.epam.ta.reportportal.ws.model.issue.Issue;
import com.epam.ta.reportportal.ws.model.launch.StartLaunchRQ;
import io.reactivex.Maybe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import rp.com.google.common.base.Supplier;

import java.util.Calendar;
import java.util.Date;

import static rp.com.google.common.base.Strings.isNullOrEmpty;

@Listeners({ IssueInvalidReport.ExtendedListener.class })
public class IssueInvalidReport {
	private static final Logger LOGGER = LoggerFactory.getLogger(IssueInvalidReport.class);

	@Test
	public void testForceFinishIssue() {
		Assert.fail();
	}

	public static final class ExtendedTestNGService extends TestNGService {
		private static Supplier<Launch> myLaunch;

		public ExtendedTestNGService() {
			super(getLaunch());
		}

		private static final Supplier<Launch> getLaunch() {
			final ReportPortal reportPortal = ReportPortal.builder().build();
			ListenerParameters parameters = reportPortal.getParameters();
			StartLaunchRQ rq = new StartLaunchRQ();
			rq.setName(parameters.getLaunchName());
			rq.setStartTime(Calendar.getInstance().getTime());
			rq.setAttributes(parameters.getAttributes());
			rq.setMode(parameters.getLaunchRunningMode());
			rq.setRerun(parameters.isRerun());
			if (!isNullOrEmpty(parameters.getRerunOf())) {
				rq.setRerunOf(parameters.getRerunOf());
			}
			if (!isNullOrEmpty(parameters.getDescription())) {
				rq.setDescription(parameters.getDescription());
			}
			myLaunch = () -> reportPortal.newLaunch(rq);
			return myLaunch;
		}

		@Override
		public void finishTestMethod(String status, ITestResult testResult) {
			FinishTestItemRQ rq = new FinishTestItemRQ();
			rq.setEndTime(new Date(testResult.getEndMillis()));
			rq.setStatus(status);
			Issue issue = new Issue();
			issue.setIssueType(null);
			rq.setIssue(issue);
			Maybe<String> idm = this.getAttribute(testResult, RP_ID);
			idm.subscribe(LOGGER::info);
			myLaunch.get().finishTestItem(idm, rq);
		}
	}

	public static final class ExtendedListener extends BaseTestNGListener {
		public ExtendedListener() {
			super(new ExtendedTestNGService());
		}
	}
}
