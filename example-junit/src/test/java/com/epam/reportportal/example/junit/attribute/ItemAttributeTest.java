package com.epam.reportportal.example.junit.attribute;

import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import com.epam.reportportal.annotations.attribute.MultiKeyAttribute;
import com.epam.reportportal.annotations.attribute.MultiValueAttribute;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author <a href="mailto:ivan_budayeu@epam.com">Ivan Budayeu</a>
 */
public class ItemAttributeTest {

	@Test
	@Attributes(attributes = { @Attribute(key = "key", value = "value") })
	public void first() {
		Assert.assertEquals(1, 1);
	}

	@Test
	@Attributes(attributes = { @Attribute(key = "key1", value = "value1"),
			@Attribute(key = "key2", value = "value2") }, multiKeyAttributes = { @MultiKeyAttribute(keys = { "k1", "k2" }, value = "v") })
	public void second() {
		Assert.assertEquals(1, 1);
	}

	@Test
	@Attributes(multiValueAttributes = { @MultiValueAttribute(isNullKey = true, values = { "v1", "v2" }) })
	public void third() {
		Assert.assertEquals(1, 1);
	}
}
