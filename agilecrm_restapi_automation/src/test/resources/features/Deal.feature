Feature: Verify deal feature

  @CreateDeal
  Scenario Outline: verify create deal api
    Given I prepare request structure to create deal
      | name   | expectedValue   | probability   | milestone   | contactIds   | customData   |
      | <name> | <expectedValue> | <probability> | <milestone> | <contactIds> | <customData> |
    When I hit an api for deal
      | endpoint   | /opportunity |
      | pathParam  |              |
      | httpMethod | POST         |
    Then I verify deal created successfully using <statusCode>
      | name   | expectedValue   | probability   | milestone   | contactIds   | customData   |
      | <name> | <expectedValue> | <probability> | <milestone> | <contactIds> | <customData> |
    Examples:
      | name  | expectedValue | probability | milestone | contactIds     | customData     | statusCode |
      | Deal2 | 500.0f        | 100         | Won       |                | Group Size, 10 | 200        |
      |       | 500.0f        | 100         | Won       |                | Group Size, 10 | 200        |
      | Deal2 | 68767         | 100         | Won       |                | Group Size, 10 | 200        |
      | Deal2 | 500.0f        | 100         | test      |                | Group Size, 10 | 200        |
      | Deal2 | 500.0f        | 100         | Won       | 58758,78585    | Group Size, 10 | 200        |
      | Deal2 | 500.0f        | 100         | Won       |                |                | 200        |
      | Deal2 | 500.0f        | 1000        | Won       |                |                | 200        |
      | Deal2 | 500.0f        | -10         | Won       |                |                | 200        |
      | Deal2 | 500.0f        | -10         |           |                |                | 400        |
      | Deal2 | 500.0f        | true        | Won       |                | Group Size, 10 | 400        |
      | Deal2 | 500.0f        | 100         | Won       | dysdt,sajdfjsa | Group Size, 10 | 400        |
      | Deal2 | test          | 100         | Won       |                | Group Size, 10 | 400        |
      |       |               |             |           |                |                | 400        |
      | Deal2 | 500.0f        | 100         | 13243     |                | Group Size, 10 | 200        |
      | Deal2 | 500.0f        | 100f        | Won       |                | Group Size, 10 | 400        |

  @GetDealsOfCurrentUser
  Scenario Outline: Verify get deals of current user api
    Given I prepare request structure to get the deals
      | username | <username>              |
      | password | <password>              |
      | header   | Accept:application/json |
    When I hit an api for deal
      | endpoint   | opportunity/my/deals |
      | httpMethod | GET                  |
      | valid      | <valid>              |
    Then I verify the deals information using
      | username | <username> |
    Examples:
      | valid | username              | password |
      | true  | usha.2310@yopmail.com | CGPYWLPE |

  @GetDealById
  Scenario Outline: Verify get deal by id api
    Given I prepare request structure to get deal by id
    When I hit an api for deal
      | endpoint   | opportunity/ |
      | pathParam  | <pathParam>  |
      | httpMethod | GET          |
    Then I verify the deal information using "<pathParam>" and status code should be <statusCode>
      | valid | <valid> |
    Examples:
      | pathParam        | valid | statusCode |
      | 5449471651807232 | true  | 200        |
      | null             | true  | 200        |
      |                  | true  | 200        |
      | 56342378         | false | 204        |
      | #@$%^            | false | 400        |
      | assdff           | false | 400        |
