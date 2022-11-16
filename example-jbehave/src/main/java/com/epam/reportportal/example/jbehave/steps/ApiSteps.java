package com.epam.reportportal.example.jbehave.steps;

import com.epam.reportportal.service.ReportPortal;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.jbehave.core.annotations.*;
import org.jbehave.core.model.ExamplesTable;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ApiSteps {
	private static final Logger logger = LoggerFactory.getLogger(ApiSteps.class);

	@Given("preparedStep1")
	public void preparedStep1() {
		logger.info("preparedStep1");
	}

	@Given("preparedStep2")
	public void preparedStep2() {
		logger.info("preparedStep2");
	}

	@Given("Precondition")
	@Composite(steps = { "Given preparedStep1", "Given preparedStep2" })
	public void precondition() throws IOException {
		// Report launch log
		File file = File.createTempFile("rp-test", ".css");
		Resources.asByteSource(Resources.getResource("files/css.css")).copyTo(Files.asByteSink(file));
		ReportPortal.emitLaunchLog("LAUNCH LOG MESSAGE WITH ATTACHMENT", "error", new Date(), file);

		logger.info("It is a composite step which contain preparedStep1 and  preparedStep2");
	}

	@When("Get Dashboards: $table")
	public void getDashboards(final ExamplesTable table) {
		logger.info(
				"Get Dashboards with: projectName {}, dashboardId {}",
				table.getRow(0).get("projectName"),
				table.getRow(0).get("dashboardId")
		);
	}

	@When("Get Dashboard: $table")
	@Alias("Request Dashboard: $table")
	public void getDashboard(final ExamplesTable table) {
		logger.info(
				"Get Dashboard with: projectName {}, dashboardId {}",
				table.getRow(0).get("projectName"),
				table.getRow(0).get("dashboardId")
		);
	}

	@When("Post Dashboard: $table")
	public void createDashboard(final ExamplesTable table) {
		logger.info("Post Dashboard");
	}

	@When("Delete Dashboard: $table")
	public void deleteDashboard(final ExamplesTable table) throws InterruptedException {
		logger.info("Delete Dashboard");
	}

	@Then("Dashboard response should contain: $table")
	public void verifyDashboard(final ExamplesTable table) {
		logger.info("Dashboard response matches to the expected one");
	}

	@Then("Dashboard responses should contain: $table")
	public void verifyDashboards(final ExamplesTable table) {
		logger.info("Dashboard responses matches to the expected one");
	}

	@When("Get Widget names: $table")
	public void getWidgetNames(final ExamplesTable table) {
		logger.info("Get Widget names");
	}

	@Given("Get Widget: $table")
	public void getWidget(final ExamplesTable table) {
		logger.info("Get Widget");

	}

	@Then("Widget names response should contain <names>")
	public void verifyWidgetNames(@Named("names") String names) {
		logger.info("Widget names response equals to {}", names);
	}

	@Then("Widget response should contain: $table")
	public void verifyWidget(final ExamplesTable table) {
		Assertions.fail("Widget response doesn't match to the expected one");
	}
}
