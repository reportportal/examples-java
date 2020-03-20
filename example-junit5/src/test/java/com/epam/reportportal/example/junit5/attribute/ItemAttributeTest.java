package com.epam.reportportal.example.junit5.attribute;

import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import com.epam.reportportal.annotations.attribute.MultiKeyAttribute;
import com.epam.reportportal.annotations.attribute.MultiValueAttribute;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author <a href="mailto:ivan_budayeu@epam.com">Ivan Budayeu</a>
 */
//@ExtendWith(ReportPortalExtension.class)
public class ItemAttributeTest {

	@Test
	@Attributes(attributes = { @Attribute(key = "key", value = "value") })
	public void first() {
		Assertions.assertEquals(1, 1);
	}

	@Test
	@Attributes(attributes = { @Attribute(key = "key1", value = "value1"),
			@Attribute(key = "key2", value = "value2") }, multiKeyAttributes = { @MultiKeyAttribute(keys = { "k1", "k2" }, value = "v") })
	public void second() {
		Assertions.assertEquals(1, 1);
	}

	@Test
	@Attributes(multiValueAttributes = { @MultiValueAttribute(isNullKey = true, values = { "v1", "v2" }) })
	public void third() {
		Assertions.assertEquals(1, 1);
	}

	@Tag("fourth")
	@Test
	void fourth() {
		assertTrue(true);
	}
}
