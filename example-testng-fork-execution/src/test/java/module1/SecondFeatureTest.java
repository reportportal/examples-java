package module1;

import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SecondFeatureTest {
    @Attributes(attributes = {
            @Attribute(key = "tmsLink", value = "TEST-102")
    })
    @Test
    public void firstAppSecondFeatureTest() {
        Assert.assertTrue(true, "Fake test");
    }
}
