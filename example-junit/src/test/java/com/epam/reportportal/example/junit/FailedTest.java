package com.epam.reportportal.example.junit;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

@Ignore
public class FailedTest {

//    @Ignore
    @Test
    public void expectToFail() {
        Assert.assertEquals(3, 3);
    }
}
