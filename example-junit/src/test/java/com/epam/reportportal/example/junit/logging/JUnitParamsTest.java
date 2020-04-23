package com.epam.reportportal.example.junit.logging;

import java.util.Map;

import com.nordstrom.automation.junit.AtomicTest;
import com.nordstrom.automation.junit.LifecycleHooks;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.model.ReflectiveCallable;
import org.junit.runner.Description;
import org.junit.runner.RunWith;

import com.google.common.base.Optional;
import com.nordstrom.automation.junit.ArtifactParams;
import com.nordstrom.automation.junit.AtomIdentity;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class JUnitParamsTest implements ArtifactParams {
	
	@Rule
	public final AtomIdentity identity = new AtomIdentity(this);

	@Override
	public AtomIdentity getAtomIdentity() {
		return identity;
	}

	@Override
	public Description getDescription() {
		return identity.getDescription();
	}

	@Override
	public Optional<Map<String, Object>> getParameters() {
		Object runner = LifecycleHooks.getRunnerForTarget(this);
		AtomicTest test = LifecycleHooks.getAtomicTestOf(runner);
		ReflectiveCallable callable = LifecycleHooks.getCallableOf(runner, test.getIdentity());
		try {
			Object[] params = LifecycleHooks.getFieldValue(callable, "val$params");
			Integer age = (Integer) params[0];
			Boolean valid = (Boolean) params[1];
			return Param.mapOf(Param.param("age", age), Param.param("valid", valid));
		} catch (IllegalAccessException | NoSuchFieldException e) {
			return Optional.absent();
		}
    }
    
    @Test
    @Parameters({ "17, false", "22, true" })
    public void logPlain(int age, boolean valid) {
        assertThat(valid, equalTo(age >= 18));
    }
}
