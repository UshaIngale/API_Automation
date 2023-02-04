Feature: Verify contact functionality of agilecrm application

  @CreateContactBySerialization
  Scenario Outline: Verify the create contact feature
    Given I create request structure to create contact
      | username  | usha.2310@yopmail.com |
      | password  | CGPYWLPE              |
      | basePath  | /dev/api              |
      | type      | <type>                |
      | source    | <source>              |
      | tags      | <tags>                |
      | firstname | <firstName>           |
      | lastname  | <lastName>            |
      | email     | <email>               |
      | starValue | <starValue>           |
      | leadScore | <leadScore>           |
    When I hit an api to create contact
      | endpoint   | /contacts |
      | pathParam  |           |
      | httpMethod | POST      |
    Then I verify the contact is created successfully <statusCode>

    And I verify newly created contact in get by id api
      | username   | usha.2310@yopmail.com |
      | password   | CGPYWLPE              |
      | basePath   | /dev/api              |
      | endpoint   | /contacts/            |
      | pathParam  |                       |
      | httpMethod | GET                   |

    Examples:
      | valid | type   | leadScore | starValue | tags         | firstName   | lastName   | email   | statusCode |
      | true  | PERSON | 100       | 4         | sanity,smoke | RestAssured | Automation | valid   | 200        |
      | false | PERSON | 89        | 5         | sanity,smoke |             | Automation | valid   | 400        |
      | false | PERSON | 99        | 3         | sanity,smoke | API         |            | valid   | 200        |
      | false | XYZ    | 129       | 2         | sanity,smoke |             |            | valid   | 400        |
      | false | XYZ    | 67        | 4         | sanity,smoke |             | Automation | invalid | 400        |
      | false | XYZ    | 213       | 4         | sanity,smoke |             |            | invalid | 400        |

#    Scenario: Verify get all contacts api
#      Given I prepare request structure to get all contacts
#      When I hit an api to get contacts
  @GetContact
  Scenario Outline: Verify get contact api
    Given I prepare request structure to get the contact
      | username  | usha.2310@yopmail.com   |
      | password  | CGPYWLPE                |
      | header    | Accept:application/json |
      | pathParam | <pathParam>             |
    When I hit an get contact api
      | endpoint  | contacts    |
      | pathParam | <pathParam> |
    Then I verify the contact information using "<pathParam>" and status code should be <statusCode>
      | valid | <valid> |
    Examples:
      | pathParam        | valid | statusCode |
      | 5684773758763008 | true  | 200        |
      | null             | true  | 200        |
      |                  | true  | 200        |
      | 56342378         | false | 204        |
      | #@$%^            | false | 400        |
      | assdff           | false | 400        |

  @GetContactsMissingEmail
  Scenario: Verify missing email ids in contacts
    Given I prepare request structure to get the contacts missing email
#      | username | usha.2310@yopmail.com   |
#      | password | CGPYWLPE                |
      | header | Accept:application/json |
    When I hit an api to get the contacts missing email
      | endpoint   | contacts |
      | pathParam  |          |
      | httpMethod | GET      |
    Then I verify missing email ids in contact

  @GetContactsNotAssociatedWithCompany
  Scenario: Get contact information who is not associated with company
    Given I prepare request structure to get the contact information who is not associated with company
    When I hit an api
      | endpoint   | contacts |
      | pathParam  |          |
      | httpMethod | GET      |
    Then I print all contact info who is not associated to company

  @SearchContact
  Scenario: Search contact information
    Given I prepare request structure to search contact
      | queryParam | q:Shivansh,type:PERSON |
    When I hit an api
      | endpoint   | /search |
      | pathParam  |         |
      | httpMethod | GET     |
    Then I verify the contact should be listed in the response
      | queryParam | q:Shivansh,type:PERSON |

  @GetContactList
  Scenario: Verify get contact api
    Given I prepare request structure to get the contact list
    When I hit an api
      | endpoint   | contacts |
      | httpMethod | GET      |
    Then I verify the status code should be 200

#  @UpdateContactById
#  Scenario Outline: Verify update contact by id api
#    Given I prepare request structure to update the contact by id
#      | firstname | <firstName> |
#    When I hit an api
#      | endpoint   | contacts         |
#      | pathParam  | 5684773758763008 |
#      | httpMethod | PUT              |
#    Then I verify the status code should be 200 to update contact by id
#    Examples:
#      | firstName |
#      | Keshu     |


