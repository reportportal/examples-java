package com.epam.reportportal.example.junit.annotated;

import com.epam.reportportal.annotations.TestCaseId;
import org.junit.Assert;
import org.junit.Test;

public class TestCaseIdTest {

    @TestCaseId("JU4.1.0")
    @Test
    public void simpleTest() {
        Assert.assertEquals(1, 1);
    }

}
