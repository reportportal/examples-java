package com.epam.reportportal.example.junit5.logging;

import com.epam.reportportal.example.junit5.LoggingUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import rp.com.google.common.io.Files;
import rp.com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;

@DisplayName("Description for Suite")
public class DescriptionSupportTest {

    @Test
    @DisplayName("Description for test")
    public void logCss() throws IOException {
        File file = File.createTempFile("rp-test", ".css");
        Resources.asByteSource(Resources.getResource("files/css.css")).copyTo(Files.asByteSink(file));
        LoggingUtils.log(file, "I'm logging CSS");
    }
}
