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

package com.epam.reportportal.example.cucumber4.reporter;

import com.epam.reportportal.cucumber.ScenarioReporter;
import com.epam.reportportal.utils.MemoizingSupplier;
import com.epam.ta.reportportal.ws.model.StartTestItemRQ;
import cucumber.api.TestCase;
import cucumber.api.TestStep;
import io.reactivex.Maybe;

import javax.annotation.Nonnull;
import java.util.Calendar;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * A customized reporter which does not create `Root User Story` suite
 */
public class ScenarioReporterNoSuite extends ScenarioReporter {

	@Override
	@Nonnull
	protected Optional<Maybe<String>> getRootItemId() {
		return Optional.empty();
	}

	@Override
	protected void startRootItem() {
	}

	@Override
	protected void finishRootItem() {
	}
}
