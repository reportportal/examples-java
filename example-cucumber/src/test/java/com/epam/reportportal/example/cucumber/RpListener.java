package com.epam.reportportal.example.cucumber;

import com.epam.reportportal.cucumber.StepReporter;
import com.epam.reportportal.service.ReportPortal;
import rp.org.apache.http.conn.ssl.NoopHostnameVerifier;
import rp.org.apache.http.impl.client.HttpClients;

public class RpListener extends StepReporter {
	@Override
	protected ReportPortal buildReportPortal() {
		return ReportPortal.builder().withHttpClient(HttpClients.custom().
				setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)).build();
	}
}
