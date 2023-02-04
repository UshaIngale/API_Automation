package stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import util.BaseClass;

import java.util.Map;

public class EventStepDef extends BaseClass {

    @Given("I prepare request structure to get the event")
    public void iPrepareReuestStructureToGetTheEvent(){
        prepareRequestStructure();
    }

    @When("I hit an for event")
    public void iHitAnForEvent(Map<String,String> table) {
        response=iHitApi(table);
        response.prettyPrint();
    }

    @Then("I verify the event information using {string} and status code should be {int}")
    public void iVerifyTheEventInformationUsingAndStatusCodeShouldBeStatusCode(String expectedId,int statusCode,Map<String,String> table) {
        int actualStatusCode=response.statusCode();
        Assert.assertEquals(statusCode,actualStatusCode);
        boolean valid=Boolean.parseBoolean(table.get("valid"));

        if(valid) {
            String actualId=response.jsonPath().getString("id");
            System.out.println(actualId);
            if(!expectedId.equals("null") && !expectedId.equals("")){
                Assert.assertEquals(expectedId,actualId);
            }
            System.out.println("Get event by id Test case passed");
        }
        else
            System.out.println("Get event by id Test case failed");
        System.out.println("----------------------------------------------------------------------");
    }

}
