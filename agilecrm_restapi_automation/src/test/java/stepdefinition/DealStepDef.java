package stepdefinition;

import com.agilecrm.types.deal.CustomDataDto;
import com.agilecrm.types.deal.DealDto;
import com.agilecrm.types.deal.DealResponseDto;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.junit.Assert;
import util.BaseClass;
import util.Utility;

import java.util.*;

public class DealStepDef extends BaseClass {
    DealDto dealDto;

    @Given("I prepare request structure to create deal")
    public void prepareRequestStructure(DataTable table) {
        Utility utility = new Utility();
        dealDto = new DealDto();
        Map<String, String> dataTable = table.asMaps().get(0);
        dealDto.setName(dataTable.get("name"));
        dealDto.setExpected_value(dataTable.get("expectedValue"));
        dealDto.setProbability(dataTable.get("probability"));
        dealDto.setMilestone(dataTable.get("milestone"));
        dealDto.setClose_date(1455042600);
        List<Long> contactIds = utility.setContactListForDeal(dataTable);
        dealDto.setContact_ids(contactIds);

        List<CustomDataDto> custom_data = utility.setCustomDataForDeal(dataTable);
        dealDto.setCustom_data(custom_data);
        requestSpecification=prepareRequestStructure();
        requestSpecification.header("Content-Type", ContentType.JSON)
                .body(dealDto);

    }

    @When("I hit an api for deal")
    public void iHitAnApiToCreateDeal(Map<String, String> table) {
        response = iHitApi(table);
        response.prettyPrint();
        System.out.println("----------------------------------------------------------------------");
    }

    @Then("I verify deal created successfully using {int}")
    public void iVerifyDealCreatedSuccessfullyUsing(int statusCode, DataTable table) {
        Map<String, String> dataTable = table.asMaps().get(0);
        Assert.assertEquals(statusCode,response.statusCode());
        DealResponseDto dealResponseDto = response.as(DealResponseDto.class);

        System.out.println(dealResponseDto.getId());

        Assert.assertEquals(dealDto.getName(), dealResponseDto.getName());

        Assert.assertTrue(Objects.nonNull(dealResponseDto.getId()));


        Optional.of(dealDto.getExpected_value()).ifPresent(val -> {
            Float expectValue = Float.parseFloat(val);
            Float actualValue = Float.valueOf(String.valueOf(dealResponseDto.getExpected_value()));
            Assert.assertEquals(expectValue, actualValue);
        });
        //get the custom data object from request body
        CustomDataDto expectedCustomDataDto = dealDto.getCustom_data().get(0);
        String expectedCustomDataName = expectedCustomDataDto.getName(); // get the name attribute value
        String expectedCustomDataValue = expectedCustomDataDto.getValue(); // get the value attribute value

        //get the custom data object from response body from deserialized object
        CustomDataDto actualCustomDataDto = dealResponseDto.getCustom_data().get(0);
        String actualCustomDataName = actualCustomDataDto.getName();
        String actualCustomDataValue = actualCustomDataDto.getValue();

        // compare actual and expected custom data objects
        Assert.assertEquals(expectedCustomDataName, actualCustomDataName);
        Assert.assertEquals(expectedCustomDataValue, actualCustomDataValue);

        System.out.println(dealResponseDto.getOwner().getId());
        System.out.println(dealResponseDto.getOwner().getDomain());

    }

    @Given("I prepare request structure to get deal by id")
    public void iPrepareRequestStructureToGetDealById() {
        requestSpecification=prepareRequestStructure();
    }

    @Then("I verify the deal information using {string} and status code should be {int}")
    public void iVerifyTheDealInformationUsingAndStatusCodeShouldBeStatusCode(String expectedId,int statusCode,Map<String,String> table) {
        int actualStatusCode=response.statusCode();
        Assert.assertEquals(statusCode,actualStatusCode);
        boolean valid=Boolean.parseBoolean(table.get("valid"));

        if(valid) {
            String actualId=response.jsonPath().getString("id");
            System.out.println(actualId);
            if(!expectedId.equals("null") && !expectedId.equals("")){
                Assert.assertEquals(expectedId,actualId);
            }
            System.out.println("Get deal by id Test case passed");
        }
        else
            System.out.println("Get deal by id Test case failed");
        System.out.println("----------------------------------------------------------------------");
    }

    @Given("I prepare request structure to get the deals")
    public void iPrepareRequestStructureToGetTheDeals(Map<String,String> table) {
        prepareRequestStructure(table);

    }

    @Then("I verify the deals information using")
    public void iVerifyTheDealsInformationUsing(Map<String,String> table) {
        String expectedUsername=table.get("username");

        ArrayList<Map<String,Object>> al=response.jsonPath().get("owner");   //email
        String actualUsername=(String)al.get(2).get("email");

        Assert.assertEquals(expectedUsername,actualUsername);
        System.out.println("Expected user : "+expectedUsername);
        System.out.println("Actual user : "+actualUsername);

        if(actualUsername.equals(expectedUsername))
            System.out.println("Get deals of current user Test case is passed");
        else
            System.out.println("Get deals of current user Test case is failed");
        System.out.println("----------------------------------------------------------------------");
    }
}
