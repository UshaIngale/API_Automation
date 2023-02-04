package stepdefinition;

import com.agilecrm.types.contact.ContactResponseDto;
import com.agilecrm.types.user.UserDto;
import com.agilecrm.types.user.UserResponseDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.util.Asserts;
import org.junit.Assert;
import util.BaseClass;
import util.Utility;

import java.util.Map;

public class UserStepDef extends BaseClass {
    UserDto userDto;
    UserResponseDto userResponseDto;
    String email;
    @Given("I prepare request structure to create user")
    public void createUser(Map<String,String> table){
        if (table.get("email").equals("valid")) {
            Utility utility = new Utility();
            email = utility.generateRandomEmail(10);
        }
        userDto=new UserDto();
        userDto.setName(table.get("name"));
        userDto.setGender(table.get("gender"));
        userDto.setEmail(email);
        userDto.setStatus(table.get("status"));

        requestSpecification= RestAssured.given();
        requestSpecification.baseUri("https://gorest.co.in/")
                .basePath("public/v2/")
                .header("Accept", ContentType.JSON)
                .header("Content-Type",ContentType.JSON)
                .header("Authorization", "Bearer "+"e4bc946a9bffb29f173f9d1eeba2188c538cf582840ecb939bdfd6b355bac63b")
                .body(userDto)
                .log().all();
    }

    @When("I hit an api for user")
    public void iHitAnApiForUser(Map<String,String> table) {
        response=iHitApi(table);
        response.prettyPrint();
    }

    @Then("I verify create user api with the status code {int}")
    public void iVerifyCreateUserApiWithTheStatusCodeStatusCode(int expectedStatusCode,Map<String,String> table) {
        Assert.assertEquals(expectedStatusCode,response.statusCode());
        if(response.statusCode()==201){
            userResponseDto=new UserResponseDto();
            userResponseDto = response.body().as(UserResponseDto.class);
            Assert.assertEquals(userDto.getName(), userResponseDto.getName());
            Assert.assertEquals(userDto.getGender(), userResponseDto.getGender());
            Assert.assertEquals(userDto.getEmail(), userResponseDto.getEmail());
            Assert.assertEquals(userDto.getStatus(), userResponseDto.getStatus());
        }

    }

    @Given("I prepare request structure to get user")
    public void iPrepareRequestStructureToGetUser() {
        requestSpecification.baseUri("https://gorest.co.in/")
                .basePath("public/v2/")
                .header("Accept", ContentType.JSON)
                .header("Authorization", "Bearer "+"e4bc946a9bffb29f173f9d1eeba2188c538cf582840ecb939bdfd6b355bac63b")
                .log().all();
    }

    @Then("I verify get user api with the status code {int}")
    public void iVerifyGetUserApiWithTheStatusCodeStatusCode(int expectedStatusCode ,Map<String,String> table) {
        Assert.assertEquals(expectedStatusCode, response.statusCode());
        if (response.statusCode() == 201) {
            userResponseDto = new UserResponseDto();
            userResponseDto = response.body().as(UserResponseDto.class);
            if(Boolean.parseBoolean(table.get("valid")) && table.get("pathParam")!=null){
                Assert.assertEquals(table.get("pathParam"),userResponseDto.getId());
            }
        }
    }

    @Given("I prepare request structure to delete user")
    public void iPrepareRequestStructureToDeleteUser() {
        requestSpecification.baseUri("https://gorest.co.in/")
                .basePath("public/v2/")
                .header("Accept", ContentType.JSON)
                .header("Authorization", "Bearer "+"e4bc946a9bffb29f173f9d1eeba2188c538cf582840ecb939bdfd6b355bac63b")
                .log().all();
    }

    @Then("I verify to delete user api with the status code {int}")
    public void iVerifyToDeleteUserApiWithTheStatusCodeStatusCode(int expectedStatusCode,Map<String,String> table) {
        Assert.assertEquals(expectedStatusCode,response.statusCode());
    }

    @Given("I prepare request structure to update user")
    public void iPrepareRequestStructureToUpdateUser(Map<String,String> table) {
        if (table.get("email").equals("valid")) {
            Utility utility = new Utility();
            email = utility.generateRandomEmail(10);
        }
        userDto=new UserDto();
        userDto.setName(table.get("name"));
        userDto.setGender(table.get("gender"));
        userDto.setEmail(email);

        response=requestSpecification.get("https://gorest.co.in/public/v2/users/"+table.get("pathParam"));
        userResponseDto=new UserResponseDto();
        userResponseDto = response.body().as(UserResponseDto.class);
        userDto.setStatus(userResponseDto.getStatus());

        requestSpecification= RestAssured.given();
        requestSpecification.baseUri("https://gorest.co.in/")
                .basePath("public/v2/")
                .header("Accept", ContentType.JSON)
                .header("Content-Type",ContentType.JSON)
                .header("Authorization", "Bearer "+"e4bc946a9bffb29f173f9d1eeba2188c538cf582840ecb939bdfd6b355bac63b")
                .body(userDto)
                .log().all();
    }

    @Then("I verify update user api with the status code {int}")
    public void iVerifyUpdateUserApiWithTheStatusCodeStatusCode(int expectedStatusCode,Map<String,String> table) {
        Assert.assertEquals(expectedStatusCode,response.statusCode());
        if(response.statusCode()==201){
            userResponseDto=new UserResponseDto();
            userResponseDto = response.body().as(UserResponseDto.class);
            Assert.assertEquals(userDto.getName(), userResponseDto.getName());
            Assert.assertEquals(userDto.getGender(), userResponseDto.getGender());
            Assert.assertEquals(userDto.getEmail(), userResponseDto.getEmail());
            Assert.assertEquals(userDto.getStatus(), userResponseDto.getStatus());
        }


    }
}
