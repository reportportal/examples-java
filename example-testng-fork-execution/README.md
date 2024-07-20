# Demo

### Issue description

ReportPortal fails to identify retried TestNG tests when class order is changed in XML

### Prerequisites

        cd example-testng-fork-execution

### Happy path

        # Run tests
        mvn clean test -Dsurefire.suiteXmlFiles=suites/suite_classes.xml

        # Rerun tests
        mvn clean test -Drp.rerun=true -Dsurefire.suiteXmlFiles=suites/suite_classes.xml

Result:
- RP correctly recognizes and marks the retried tests:
![RP-handles-retry-correctly](https://github.com/user-attachments/assets/082719d1-cc7a-42bb-b481-eb7aa9c88d31)

### Unhappy path

        # Run tests
        mvn clean test -Dsurefire.suiteXmlFiles=suites/suite_classes.xml

        # Rerun tests
        mvn clean test -Drp.rerun=true -Dsurefire.suiteXmlFiles=suites/suite_classes_reordered.xml

Result:
- RP fails to recognize the retried tests and instead treats them as a new set of tests:
![RP-unrecognized-retry](https://github.com/user-attachments/assets/dfc87d5d-410c-478f-ab3d-33768147a04c)
