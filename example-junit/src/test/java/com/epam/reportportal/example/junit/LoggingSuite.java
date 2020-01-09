package com.epam.reportportal.example.junit;

import com.epam.reportportal.example.junit.logging.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ JsonLoggingTest.class, LoggingTest.class, LuckyPugTest.class, XmlLoggingBetterTest.class, XmlLoggingTest.class })
public class LoggingSuite {
}
