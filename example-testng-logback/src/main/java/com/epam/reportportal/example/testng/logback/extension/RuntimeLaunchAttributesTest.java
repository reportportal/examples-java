package com.epam.reportportal.example.testng.logback.extension;

import com.epam.reportportal.listeners.ListenerParameters;
import com.epam.reportportal.service.ReportPortal;
import com.epam.reportportal.testng.BaseTestNGListener;
import com.epam.reportportal.testng.ITestNGService;
import com.epam.reportportal.testng.TestNGService;
import com.epam.reportportal.utils.MemoizingSupplier;
import com.epam.ta.reportportal.ws.model.FinishExecutionRQ;
import com.epam.ta.reportportal.ws.model.attribute.ItemAttributesRQ;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import static java.util.Optional.ofNullable;

/**
 * Test class to demonstrate how to add runtime attributes to a Launch.
 */
@Listeners({ RuntimeLaunchAttributesTest.ExtendedListener.class })
public class RuntimeLaunchAttributesTest {
	private static final Set<ItemAttributesRQ> RUNTIME_ATTRIBUTES = Collections.newSetFromMap(new ConcurrentHashMap<>());

	@Test
	public void testRuntimeLaunchAttributes() {
		System.out.println("Test stub to demonstrate runtime launch attributes.");
		// Add runtime attributes
		RUNTIME_ATTRIBUTES.add(new ItemAttributesRQ("runtime1", "value1"));
	}

	public static class ExtendedListener extends BaseTestNGListener {
		public static final Supplier<ITestNGService> SERVICE = new MemoizingSupplier<>(() -> new TestNGService(ReportPortal.builder()
				.build()) {
			@Override
			protected FinishExecutionRQ buildFinishLaunchRq(ListenerParameters parameters) {
				FinishExecutionRQ rq = super.buildFinishLaunchRq(parameters);
				Set<ItemAttributesRQ> attributes = ofNullable(rq.getAttributes()).orElseGet(HashSet::new);
				attributes.addAll(RUNTIME_ATTRIBUTES);
				rq.setAttributes(attributes);
				return rq;
			}
		});

		public ExtendedListener() {
			super(SERVICE.get());
		}
	}
}
