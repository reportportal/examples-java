package com.epam.reportportal.example.testng.logback.extension;

import com.epam.reportportal.listeners.ItemStatus;
import com.epam.reportportal.listeners.ListenerParameters;
import com.epam.reportportal.service.ReportPortal;
import com.epam.reportportal.testng.BaseTestNGListener;
import com.epam.reportportal.testng.ITestNGService;
import com.epam.reportportal.testng.TestNGService;
import com.epam.reportportal.utils.MemoizingSupplier;
import com.epam.ta.reportportal.ws.model.FinishExecutionRQ;
import com.epam.ta.reportportal.ws.model.FinishTestItemRQ;
import com.epam.ta.reportportal.ws.model.attribute.ItemAttributesRQ;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import static java.util.Optional.ofNullable;

/**
 * Test class to demonstrate how to add runtime attributes to a Launch or a Test.
 */
@Listeners({ RuntimeAttributesTest.ExtendedListener.class })
public class RuntimeAttributesTest {
	private static final Set<ItemAttributesRQ> RUNTIME_LAUNCH_ATTRIBUTES = Collections.newSetFromMap(new ConcurrentHashMap<>());
	private static final Set<ItemAttributesRQ> RUNTIME_TEST_ATTRIBUTES = Collections.newSetFromMap(new ConcurrentHashMap<>());

	@Test
	public void testRuntimeLaunchAttributes() {
		System.out.println("Test stub to demonstrate runtime launch attributes.");
		// Add runtime attributes
		RUNTIME_LAUNCH_ATTRIBUTES.add(new ItemAttributesRQ("runtime1", "value1"));
		RUNTIME_TEST_ATTRIBUTES.add(new ItemAttributesRQ("runtime2", "value2"));
	}

	public static class ExtendedListener extends BaseTestNGListener {
		public static final Supplier<ITestNGService> SERVICE = new MemoizingSupplier<>(() -> new TestNGService(ReportPortal.builder()
				.build()) {

			// Add runtime attributes to the Test
			@Override
			@Nonnull
			protected FinishTestItemRQ buildFinishTestMethodRq(@Nonnull ItemStatus status, @Nonnull ITestResult testResult) {
				FinishTestItemRQ rq = super.buildFinishTestMethodRq(status, testResult);
				Set<ItemAttributesRQ> attributes = ofNullable(rq.getAttributes()).orElseGet(HashSet::new);
				attributes.addAll(RUNTIME_TEST_ATTRIBUTES);
				RUNTIME_TEST_ATTRIBUTES.clear();
				rq.setAttributes(attributes);
				return rq;
			}

			// Add runtime attributes to the Launch
			@Override
			protected FinishExecutionRQ buildFinishLaunchRq(ListenerParameters parameters) {
				FinishExecutionRQ rq = super.buildFinishLaunchRq(parameters);
				Set<ItemAttributesRQ> attributes = ofNullable(rq.getAttributes()).orElseGet(HashSet::new);
				attributes.addAll(RUNTIME_LAUNCH_ATTRIBUTES);
				rq.setAttributes(attributes);
				return rq;
			}
		});

		public ExtendedListener() {
			super(SERVICE.get());
		}
	}
}
