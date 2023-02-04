Feature: Verify event functionality of agilecrm application

  @GetEvent
  Scenario Outline: Verify get event api
    Given I prepare request structure to get the event
    When I hit an for event
      | endpoint   | events/     |
      | pathParam  | <pathParam> |
      | httpMethod | GET         |
    Then I verify the event information using "<pathParam>" and status code should be <statusCode>
      | valid | <valid> |
    Examples:
      | pathParam        | valid | statusCode |
      | 5295231448973312 | true  | 200        |
      | null             | true  | 200        |
      |                  | true  | 200        |
      | 56342378         | false | 204        |
      | #@$%^            | false | 400        |
      | assdff           | false | 400        |