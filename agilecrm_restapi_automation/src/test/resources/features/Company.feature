Feature: Verify company functionality of agilecrm application

  @CreateCompany
  Scenario Outline: Verify create company functionality of agilecrm application
    Given I create request structure to create company
      | username    | usha.2310@yopmail.com |
      | password    | CGPYWLPE              |
      | type        | <type>                |
      | starValue   | <starValue>           |
      | leadScore   | <leadScore>           |
      | tags        | <tags>                |
      | companyType | <companyType>         |
      | companyName | <companyName>         |
      | url         | <url>                 |
      | email       | <email>               |


    When I hit the create company api
      | endpoint   | /contacts |
      | pathParam  |           |
      | httpMethod | POST      |
    Then I verify the company is created successfully using <statusCode>

    Examples:
      | valid | type    | tags      | starValue | leadScore | companyType | companyName | url                       | email               | statusCode |
      | true  | COMPANY | India,USA | 5         | 120       | MNC         | Google      | https://www.google.co.in/ | google@yopmail1.com | 200        |
      | false | PERSON  | India,USA |           |           | 123         | Google      | https://www.google.co.in/ | google              | 400        |
      | false |         | India,USA | abc       |           | MNC         | Google      | https://www.google.co.in/ | google@gmail2.com   | 200        |
      | false | COMPANY | India,USA |           | nxj       | MNC         |             | https://www.google.co.in/ | 31276746            | 400        |
      | false | COMPANY | India,USA |           |           |             | Google      |                           | google@gmail4.com   | 200        |
      | false | COMPANY | India,USA | #%        | %$#       | MNC         | jknj13563   | https://www.google.co.in/ |                     | 200        |
      | false | COMPANY | India,USA | 4         | 100       | MNC         | Google      | https://www.google.co.in/ | google@yopmail1.com | 400        |
      | false | COMPANY |           |           |           |             |             |                           |                     | 400        |

  @GetCompany
  Scenario Outline: Verify get company api
    Given I prepare request structure to get the company
    When I hit an api for company
      | endpoint   | contacts/   |
      | pathParam  | <pathParam> |
      | httpMethod | GET         |
    Then I verify the company information using "<pathParam>" and status code should be <statusCode>
      | valid | <valid> |
    Examples:
      | pathParam        | valid | statusCode |
      | 4806012662251520 | true  | 200        |
      | null             | true  | 200        |
      |                  | true  | 200        |
      | 56342378         | false | 204        |
      | #@$%^            | false | 400        |
      | assdff           | false | 400        |

  @GetCompaniesList
  Scenario: Verify get company list api
    Given I prepare request structure to get the company list
    When I hit an api for company
      | endpoint   | contacts/companies/list |
      | httpMethod | POST                    |
    Then I verify the company list response

  @DeleteSingleCompany
  Scenario Outline: Verify delete single company api
    Given I prepare request structure to delete company
    When I hit an api for company
      | endpoint   | contacts/   |
      | pathParam  | <pathParam> |
      | httpMethod | DELETE         |
    Then I verify the company should be deleted and response status code should be <statusCode>
    Examples:
      | pathParam        | valid | statusCode |
      | 4806012662251520 | true  | 204        |
      | null             | true  | 400        |
      |                  | true  | 405        |
      | 56342378         | false | 400        |
      | #@$%^            | false | 400        |
      | assdff           | false | 400        |

