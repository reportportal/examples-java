package com.epam.reportportal.example.junit.logging;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class JUnitParamsTest {

    //    @Parameters
    //    public static Collection<Object[]> data() {
    //        return Arrays.asList(new Object[][] {
    //                { 0, 0 }, { 1, 1 }//, { 2, 1 }, { 3, 2 }, { 4, 3 }, { 5, 5 }, { 6, 8 }
    //        });
    //    }
    //
    //    private int fInput;
    //
    //    private int fExpected;
    //
    //    public JUnitParamsTest(int input, int expected) {
    //        this.fInput = input;
    //        this.fExpected = expected;
    //    }

    @Test
    @Parameters({ "17, false", "22, true" })
    public void logPlain(int age, boolean valid) {
        Assert.assertTrue(true);
    }
}
