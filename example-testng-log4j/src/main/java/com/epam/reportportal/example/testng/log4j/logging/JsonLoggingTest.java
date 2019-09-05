package com.epam.reportportal.example.testng.log4j.logging;

import com.epam.reportportal.message.ReportPortalMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ObjectMessage;
import org.testng.annotations.Test;
import rp.com.google.common.io.BaseEncoding;
import rp.com.google.common.io.Files;
import rp.com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;

/**
 * JUST an example of file logging
 *
 * @author Andrei Varabyeu
 */
public class JsonLoggingTest {

    private static final Logger LOGGER = LogManager.getLogger(JsonLoggingTest.class);
    public static final String JSON_FILE_PATH = "xml/file.json";

    @Test
    public void logJsonBase64() throws IOException {
        /* here we are logging some binary data as BASE64 string */
        LOGGER.info("RP_MESSAGE#BASE64#{}#{}",
                BaseEncoding.base64().encode(Resources.asByteSource(Resources.getResource(JSON_FILE_PATH)).read()),
                "I'm logging content via BASE64");
    }

    @Test
    public void logJsonFile() throws IOException, InterruptedException {
        /* here we are logging some binary data as file (useful for selenium) */
        File file = File.createTempFile("rp-test", ".json");
        Resources.asByteSource(Resources.getResource(JSON_FILE_PATH)).copyTo(Files.asByteSink(file));

        for (int i = 0; i < 1; i++) {
            LOGGER.info("RP_MESSAGE#FILE#{}#{}", file.getAbsolutePath(), "I'm logging content via temp file");
        }
        Thread.sleep(5000L);

    }

    @Test
    public void logRepMessage() throws IOException, InterruptedException {
        /* here we are logging some binary data as file (useful for selenium) */
        File file = File.createTempFile("rp-test", ".json");
        LOGGER.info(new ObjectMessage(new ReportPortalMessage(file, "File message")));
    }

}
