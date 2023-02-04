Feature: Verify create user functionality of go rest

  @CreateUser
  Scenario Outline: Verify create user api
    Given I prepare request structure to create user
      | name   | <name>   |
      | gender | <gender> |
      | email  | <email>  |
      | status | <status> |
    When I hit an api for user
      | endpoint   | users |
      | httpMethod | POST  |
      | pathParam  |       |
    Then I verify create user api with the status code <statusCode>
      | valid | <valid> |
    Examples:
      | name        | gender | email   | status | valid | statusCode |
      | Usha Ingale | female | valid   | active | true  | 201        |
      |             | female | valid   | active | true  | 422        |
      | Usha Ingale |        | valid   | active | true  | 422        |
      | Usha Ingale | female | invalid | active | false | 422        |
      | Usha Ingale | female | valid   |        | false | 422        |
      | 83487#45    | abcd   | valid   | active | false | 422        |
      | 73467$#$    | male   | valid   | active | false | 422        |
      |             |        | invalid |        |       | 422        |

  @GetUser
  Scenario Outline: Verify get user api
    Given I prepare request structure to get user

    When I hit an api for user
      | endpoint   | users/      |
      | httpMethod | GET         |
      | pathParam  | <pathParam> |
    Then I verify get user api with the status code <statusCode>
      | pathParam | <pathParam> |
      | valid     | <valid>     |
    Examples:
      | pathParam | valid | statusCode |
      | 250918    | true  | 200        |
      |           | true  | 200        |
      | hsbh##$^% | false | 400        |

  @DeleteUser
  Scenario Outline: Verify get user api
    Given I prepare request structure to delete user

    When I hit an api for user
      | endpoint   | users/      |
      | httpMethod | DELETE      |
      | pathParam  | <pathParam> |
    Then I verify to delete user api with the status code <statusCode>
      | valid | <valid> |
    Examples:
      | pathParam | valid | statusCode |
      | 250918    | true  | 204        |
      |           | false | 404        |
      | hsbh##$^% | false | 400        |

  @UpdateUser
  Scenario Outline: Verify create user api
    Given I prepare request structure to update user
      | name   | <name>   |
      | gender | <gender> |
      | email  | <email>  |
    When I hit an api for user
      | endpoint   | users  |
      | httpMethod | PATCH  |
      | pathParam  | 251162 |
    Then I verify update user api with the status code <statusCode>
      | valid | <valid> |
    Examples:
      | name        | gender | email | status | valid | statusCode |
      | Usha Ingale | female | valid | active | true  | 201        |