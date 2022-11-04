Verify API calls related to Widget Controller functionality

Meta:
@author Ostapenko Ievgen
@category smoke
@smoke

Narrative:

In order to show the advance Widget functionality
As a user
I want to perform CRUD operations

Lifecycle:
Before:
Given Precondition

Scenario: Get all widget names
Meta:
@relatedStory OR-10019
@tag teams:Ajax, sprints:Sprint 42
@issue OR-10719
When Get Widget names:
  | projectName |
  | crt-odc     |
Then Widget names response should contain <names>

Examples:
  | names                               |
  | LATEST SINGLE STORY LAUNCH          |
  | LATEST REGRESSION                   |
  | OVERALL AMOUNT OF TESTS             |
  | REGRESSION DURATION                 |
  | PASSED TO FAILED                    |
  | FAILED TEST CASES TREND CHART       |
  | LATEST SMOKE LAUNCH                 |
  | SMOKE DURATION                      |
  | Launch statistics trend chart       |
  | DIFFERENT LAUNCHES COMPARISON CHART |
  | Unique bugs table                   |
  | Unique bugs                         |

Scenario: Get widget
Meta:
@relatedStory OR-100444
Given Get Widget:
  | projectName | widgetId                 |
  | crt-odc     | 589b1351adbe1d0006bb7b17 |
Then Widget response should contain:
  | owner               | isShared | name       |
  | yaroslav_shevchenko | true     | Onboarding |

