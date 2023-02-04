Feature: Verify campaign functionality of agilecrm application

  @GetCampaign
  Scenario: Verify get list of compaign api
    Given I prepare request structure to get the campaign
    When I hit an api for campaign
      | endpoint   | workflows |
      | httpMethod | GET       |
    Then I verify the campaign information
      | statusCode    | 200  |
      | valid         | true |
      | noOfCampaigns | 1    |
