package module1;

import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import org.testng.Assert;
import org.testng.annotations.Test;

@Attributes(attributes = {
        @Attribute(key = "tmsLink", value = "TEST-999")
})
public class FirstFeatureTest {

    @Test
    public void firstAppFirstFeatureTest() {
        Assert.assertTrue(true, "Fake test");
    }

    @Test
    public void secondAppFirstFeatureTest() {
        Assert.assertTrue(true, "Fake test");
    }
}
