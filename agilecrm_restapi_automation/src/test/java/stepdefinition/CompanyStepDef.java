package stepdefinition;

import com.agilecrm.types.company.CompanyDto;
import com.agilecrm.types.company.CompanyPropertyDto;
import com.agilecrm.types.company.CompanyPropertyResponseDto;
import com.agilecrm.types.company.CompanyResponseDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.junit.Assert;
import util.BaseClass;
import util.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CompanyStepDef extends BaseClass {
    CompanyDto companyDto;
    CompanyPropertyDto companyPropertyDto;
    CompanyResponseDto companyResponseDto;
    String expectedCompanyName;

    @Given("I create request structure to create company")
    public void prepareReuestStructure(Map<String, String> data) {
        Utility utility = new Utility();
        companyDto = new CompanyDto();
        List<CompanyPropertyDto> prop = new ArrayList<>();
        prop.add(utility.setproperty(data.get("companyType"), "Company Type", "SYSTEM", ""));
        prop.add(utility.setproperty(data.get("companyName"), "name", "SYSTEM", ""));
        prop.add(utility.setproperty(data.get("url"), "url", "SYSTEM", ""));
        prop.add(utility.setproperty(data.get("email"), "email", "SYSTEM", ""));
        companyDto.setType(data.get("type"));
        if (data.get("leadScore") != null) {
            companyDto.setLead_score(Integer.parseInt(data.get("leadScore")));
        }
        if (data.get("starValue") != null) {
            companyDto.setStar_value(Integer.parseInt(data.get("starValue")));
        }
        List<String> tags = new ArrayList<>();
        if (data.get("tags") != null) {
            String[] tags1 = data.get("tags").split(",");
            for (int i = 0; i < tags1.length; i++) {
                tags.add(tags1[i]);
            }
        }
        companyDto.setTags(tags);
        companyDto.setProperties(prop);

        requestSpecification.baseUri("https://ushatesting.agilecrm.com")
                .basePath("/dev/api/")
                .header("Accept", ContentType.JSON)
                .header("Content-Type", ContentType.JSON)
                .body(companyDto)
                .auth().basic(data.get("username"), data.get("password"))
                .log().all();
    }

    @When("I hit the create company api")
    public void iHitTheCreateCompanyApi(Map<String, String> table) {
        response = iHitApi(table);
        response.prettyPrint();
        System.out.println("----------------------------------------------------------------------");
    }

    @Then("I verify the company is created successfully using {int}")
    public void iVerifyTheCompanyIsCreatedSuccessfullyUsingStatusCode(int statusCode) {
        Assert.assertEquals(statusCode, response.statusCode());
        System.out.println(response.statusCode());
        if (response.statusCode() == 200) {
            companyResponseDto = response.body().as(CompanyResponseDto.class);
            Assert.assertEquals(companyDto.getType(), companyResponseDto.getType());
            Assert.assertEquals(companyDto.getStar_value(), companyResponseDto.getStar_value());
            Assert.assertEquals(companyDto.getLead_score(), companyResponseDto.getLead_score());
            Assert.assertEquals(companyDto.getTags(), companyResponseDto.getTags());
            for (int i = 0; i < companyDto.getProperties().size(); i++) {
                CompanyPropertyDto expectedPropertyDto = (CompanyPropertyDto) companyDto.getProperties().get(i);
                String expectedValue = expectedPropertyDto.getValue(); // get the value attribute value

                //get the properties data object from response body from deserialized object
                CompanyPropertyResponseDto actualPropertyDto = companyResponseDto.getProperties().get(i);
                String actualValue = actualPropertyDto.getValue();

                // compare actual and expected properties data objects
                Assert.assertEquals(expectedValue, actualValue);

            }
        }
    }

    @Given("I prepare request structure to get the company")
    public void iPrepareRequestStructureToGetTheCompany() {
        requestSpecification = prepareRequestStructure();
    }

    @Then("I verify the company information using {string} and status code should be {int}")
    public void iVerifyTheCompanyInformationUsingAndStatusCodeShouldBeStatusCode(String expectedId, int statusCode, Map<String, String> table) {
        int actualStatusCode = response.statusCode();
        Assert.assertEquals(statusCode, actualStatusCode);
        boolean valid = Boolean.parseBoolean(table.get("valid"));

        if (valid) {
            String actualId = response.jsonPath().getString("id");
            System.out.println(actualId);
            if (!expectedId.equals("null") && !expectedId.equals("")) {
                Assert.assertEquals(expectedId, actualId);
            }
            System.out.println("Get company by id Test case passed");
        } else
            System.out.println("Get company by id Test case failed");

        System.out.println("----------------------------------------------------------------------");
    }

    @When("I hit an api for company")
    public void iHitAnApiForCompany(Map<String, String> table) {
        response = iHitApi(table);
        response.prettyPrint();
    }

    @Then("I verify the company list response")
    public void iVerifyTheCompanyListResponse() {
        Assert.assertEquals(200, response.statusCode());
        List<Long> companyIds = response.jsonPath().getList("id");
        System.out.println("Company Ids : " + companyIds);
        List<List<Map<String, String>>> propDetails = response.jsonPath().getList("properties");
        //comparing the size of id and propDetails list
        Assert.assertEquals(companyIds.size(), propDetails.size());

        //iterate each property object
        for (int i = 0; i < companyIds.size(); i++) {
            Long id = companyIds.get(i); // get company id in list format
            List<Map<String, String>> propObject = propDetails.get(i); // get property object list
            //iterate each property object of company
            String companyName = null;
            String website = null;
            //iterate property object to get company name and website
            for (Map<String, String> prop : propObject) {
                if (prop.get("name").equals("name")) {
                    companyName = prop.get("value");
                } else if (prop.get("name") != null && prop.get("name").equals("url")) {
                    website = prop.get("value");

                }

            }
            System.out.println("Id: " + id + " Company Name: " + companyName + " Website: " + website);
        }
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Given("I prepare request structure to get the company list")
    public void iPrepareRequestStructureToGetTheCompanyList() {
        requestSpecification = prepareRequestStructure();
        requestSpecification.header("Content-Type", "application/x-www-form-urlencoded");
    }

    @Given("I prepare request structure to delete company")
    public void iPrepareRequestStructureToDeleteCompany() {
        requestSpecification=prepareRequestStructure();
    }

    @Then("I verify the company should be deleted and response status code should be {int}")
    public void iVerifyTheCompanyShouldBeDeletedAndResponseStatusCodeShouldBeStatusCode(int statusCode) {
        Assert.assertEquals(statusCode,response.statusCode());
    }
}