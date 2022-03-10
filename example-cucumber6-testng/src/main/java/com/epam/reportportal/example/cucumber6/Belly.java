/*
 * Copyright 2022 EPAM Systems
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

package com.epam.reportportal.example.cucumber6;

public class Belly {
	
	private int satiation = 0;
	
    public void eat(int cukes) {
    	satiation += cukes;
    }
    
    public void wait(int hours) {
    	if ((hours > 0) && (satiation > 0)) {
    		int utilized = 60 * hours;
    		if (utilized > satiation) {
    			utilized = satiation;
    		}
    		satiation -= utilized;
    	}
    }
    
    public boolean growl() {
    	return satiation <= 0;
    }
}
