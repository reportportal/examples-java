/*
 * Copyright 2021 EPAM Systems
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.epam.reportportal.example.spock

import org.slf4j.LoggerFactory
import spock.lang.Specification

class HelloLoggingSpec extends Specification {
    def LOGGER = LoggerFactory.getLogger(HelloLoggingSpec.class)


    def "logging in a test example"() {
        LOGGER.info("Hello! I'm logging from a test")

        expect: true
    }
}
