package stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import util.BaseClass;

import java.util.List;
import java.util.Map;

public class CampaignStepDef extends BaseClass {
    @Given("I prepare request structure to get the campaign")
    public void iPrepareRequestStructureToGetTheCampaign(){
        prepareRequestStructure();
    }

    @When("I hit an api for campaign")
    public void iHitAnApiForCampaign(Map<String,String> table) {
        response=iHitApi(table);
        response.prettyPrint();
    }

    @Then("I verify the campaign information")
    public void iVerifyTheCampaignInformation(Map<String,String> table) {
        int actualStatusCode=response.statusCode();
        int expectedStatusCode=Integer.parseInt(table.get("statusCode"));
        Assert.assertEquals(expectedStatusCode,actualStatusCode);
        boolean valid=Boolean.parseBoolean(table.get("valid"));

        if(valid) {
            List<Long> campaignIds =response.jsonPath().getList("id");
            Assert.assertEquals(Integer.parseInt(table.get("noOfCampaigns")) ,campaignIds.size());
            System.out.println(campaignIds);
            System.out.println("Get campaign list Test case passed");
        }
        else
            System.out.println("Get campaign list Test case failed");
        System.out.println("----------------------------------------------------------------------");
    }
}
