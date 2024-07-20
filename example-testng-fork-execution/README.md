# Demo

### Prerequisites

        cd example-testng-fork-execution

### Run tests

        mvn clean test \
                -DRP_API_KEY=${RP_API_KEY} \
                -Dsurefire.suiteXmlFiles=suites/suite_classes.xml
