package module1;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MyFeatureTest {
    @Test
    public void myFeature1Test() {
        Assert.assertTrue(true, "Fake test");
    }

    @Test
    public void myFeature2Test() {
        Assert.fail("Test not implemented");
    }
}
