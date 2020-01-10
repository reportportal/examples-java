package com.epam.reportportal.example.testng.logback.attribute;

import com.epam.reportportal.annotations.attribute.*;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:ivan_budayeu@epam.com">Ivan Budayeu</a>
 */
@Listeners(ReportPortalTestNGListener.class)
public class ItemAttributeTest {

	@Test
	@Attributes(attributes = { @Attribute(key = "key", value = "value") })
	public void first() {
		Assert.assertEquals(1, 1);
	}

	@Test
	@Attributes(attributeValues = { @AttributeValue(value = "value") })
	public void second() {
		Assert.assertEquals(1, 1);
	}

	@Test
	@Attributes(multiKeyAttributes = { @MultiKeyAttribute(keys = { "k1", "k2" }, value = "v") })
	public void third() {
		Assert.assertEquals(1, 1);
	}

	@Test
	@Attributes(multiValueAttributes = { @MultiValueAttribute(key = "k", values = { "v1", "v2" }) })
	public void fourth() {
		Assert.assertEquals(1, 1);
	}

	@Test
	@Attributes(multiValueAttributes = { @MultiValueAttribute(isNullKey = true, values = { "v1", "v2" }) })
	public void fifth() {
		Assert.assertEquals(1, 1);
	}

	@Test
	@Attributes(attributes = { @Attribute(key = "key1", value = "value1"),
			@Attribute(key = "key2", value = "value2") }, multiKeyAttributes = { @MultiKeyAttribute(keys = { "k1", "k2" }, value = "v") })
	public void sixth() {
		Assert.assertEquals(1, 1);
	}

}
