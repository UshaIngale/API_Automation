package stepdefinition;

import com.agilecrm.types.contact.ContactDto;
import com.agilecrm.types.contact.ContactPropertyDto;
import com.agilecrm.types.contact.ContactResponseDto;
import com.agilecrm.types.contact.PropertyResponseDto;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Assert;
import util.BaseClass;
import util.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ContactStepDef extends BaseClass {
    ContactDto contactDto;
    Utility utility;
    ContactResponseDto contactResponseDto;
    ContactPropertyDto contactPropertiesDto;
    String expectedContactName;


    @Given("I create request structure to create contact")
    public void prepareRequestStructureToCreateContact(Map<String, String> data) {
         utility = new Utility();
        contactDto = new ContactDto();
        List<ContactPropertyDto> prop = new ArrayList<>();
        prop.add(utility.setpropertyData(data.get("firstname"), "first_name", "SYSTEM", ""));
        prop.add(utility.setpropertyData(data.get("lastname"), "last_name", "SYSTEM", ""));
        prop.add(utility.setpropertyData(utility.generateRandomEmail(7), "email", "SYSTEM", "work"));
        contactDto.setType(data.get("type"));
        contactDto.setLead_score(Integer.parseInt(data.get("leadScore")));
        contactDto.setStar_value(Integer.parseInt(data.get("starValue")));
        List<String> tags = new ArrayList<>();
        String[] tags1 = data.get("tags").split(",");
        for (int i = 0; i < tags1.length; i++) {
            tags.add(tags1[i]);
        }
        contactDto.setTags(tags);
        contactDto.setProperties(prop);

        requestSpecification.baseUri("https://ushatesting.agilecrm.com")
                .basePath("/dev/api/")
                .header("Accept", ContentType.JSON)
                .header("Content-Type", ContentType.JSON)
                .body(contactDto)
                .auth().basic(data.get("username"), data.get("password"))
                .log().all();
    }

    @When("I hit an api to create contact")
    public void iHitAnApiToCreateContact(Map<String, String> table) {
        response = iHitApi(table);
        response.prettyPrint();
        System.out.println("----------------------------------------------------------------------");
    }

    @Then("I verify the contact is created successfully {int}")
    public void iVerifyTheContactIsCreatedSuccessfullyStatusCode(int statusCode) {
        Assert.assertEquals(statusCode, response.statusCode());
        System.out.println(response.statusCode());
        if (response.statusCode() == 200) {
            contactResponseDto = response.body().as(ContactResponseDto.class);
            Assert.assertEquals(contactDto.getType(), contactResponseDto.getType());
            Assert.assertEquals(contactDto.getStar_value(), contactResponseDto.getStar_value());
            Assert.assertEquals(contactDto.getLead_score(), contactResponseDto.getLead_score());
            Assert.assertEquals(contactDto.getTags(), contactResponseDto.getTags());
//        Assert.assertEquals(contactDto.getProperties(),contactResponseDto.getProperties());
            for (int i = 0; i < contactDto.getProperties().size(); i++) {
                ContactPropertyDto expectedPropertyDto = contactDto.getProperties().get(i);
                String expectedValue = expectedPropertyDto.getValue(); // get the value attribute value

                //get the properties data object from response body from deserialized object
                PropertyResponseDto actualPropertyDto = contactResponseDto.getProperties().get(i);
                String actualValue = actualPropertyDto.getValue();

                // compare actual and expected properties data objects
                Assert.assertEquals(expectedValue, actualValue);

            }
        }
    }

    @And("I verify newly created contact in get by id api")
    public void iVerifyNewlyCreatedContactInGetByIdApi(Map<String,String> table) {
//        Assert.assertEquals(200, response.statusCode());
        if (response.statusCode() == 200) {
            String endpoint = table.get("endpoint") + contactResponseDto.getId();
            requestSpecification = RestAssured.given();
            requestSpecification.baseUri("https://ushatesting.agilecrm.com")
                    .basePath("/dev/api/")
                    .header("Accept", ContentType.JSON)
                    .auth().basic(table.get("username"), table.get("password"))
                    .log().all();

            response = requestSpecification.get(endpoint);
            response.prettyPrint();
            Assert.assertEquals(200, response.statusCode());
        }
        System.out.println("----------------------------------------------------------------------");
    }

    @Given("I prepare request structure to get the contact")
    public void iPrepareRequestStructureToGetTheContact(Map<String,String> table) {
        prepareRequestStructure(table);
    }

    @When("I hit an get contact api")
    public void iHitAnGetContactApi(Map<String,String> table) {
        String endpoint;
        if (table.get("pathParam") != null && !table.get("pathParam").equals("null")) {
            endpoint = table.get("endpoint");
            System.out.println("path : " + endpoint);
            response = requestSpecification.get(endpoint + "/{contactId}");
        } else {
            System.out.println("From Else...!!!");
            endpoint = table.get("endpoint");
            response = requestSpecification.get(endpoint);
        }
        response.prettyPrint();
        System.out.println("----------------------------------------------------------------------");
        
    }

    @Then("I verify the contact information using {string} and status code should be {int}")
    public void iVerifyTheContactInformationUsingAndStatusCodeShouldBeStatusCode(String expectedId, int statusCode, Map<String, String> table) {
        int actualStatusCode = response.statusCode();
        Assert.assertEquals(statusCode, actualStatusCode);
        boolean valid = Boolean.parseBoolean(table.get("valid"));

        if (valid) {
            String actualId = response.jsonPath().getString("id");
            System.out.println(actualId);
            if (!expectedId.equals("null") && !expectedId.equals("")) {
                Assert.assertEquals(expectedId, actualId);
            }
            System.out.println("Get contact by id Test case passed");
        } else
            System.out.println("Get contact by id Test case failed");
        System.out.println("----------------------------------------------------------------------");
    }


    @Given("I prepare request structure to get the contacts missing email")
    public void iPrepareRequestStructureToGetTheContactsMissingEmail(Map<String,String> table) {
        String headers = table.get("header");
        requestSpecification.baseUri(prop.getProperty("baseUri"))
                .basePath(prop.getProperty("basePath"))
                .headers(headers.split(":")[0], headers.split(":")[1])
                .auth().basic(prop.getProperty("username"), prop.getProperty("password")).log().all();

    }

    @When("I hit an api to get the contacts missing email")
    public void iHitAnApiToGetTheContactsMissingEmail(Map<String,String> table) {
        iHitApi(table);
    }

    @Then("I verify missing email ids in contact")
    public void iVerifyMissingEmailIdsInContact() {
        List<List<Map<String, String>>> propDetails = response.jsonPath().getList("properties");
        for (List<Map<String, String>> prop : propDetails) {
            String contactName = null;
            String email = null;
            for (Map<String, String> propObject : prop) {
                if (Objects.nonNull(propObject.get("name")) && propObject.get("name").equals("first_name")) {
                    contactName = propObject.get("value"); // get the contact first name from property object
                } else if (Objects.nonNull(propObject.get("name")) && propObject.get("name").equals("email")) {
                    email = propObject.get("value");
                }
            }
            Utility utility = new Utility();
            boolean valid = utility.verifyEmail(email);
            if (valid) {
                System.out.println("Contact Name: " + contactName);
                System.out.println("Valid Email Id: " + email);
                System.out.println("------------------------------------------------------------");
            } else {
                System.out.println("Contact Name: " + contactName);
                System.out.println("Invalid Email Id: " + email);
                System.out.println("------------------------------------------------------------");
            }
        }
    }

    @Given("I prepare request structure to get the contact information who is not associated with company")
    public void iPrepareRequestStructureToGetTheContactInformationWhoIsNotAssociatedWithCompany() {
        requestSpecification=prepareRequestStructure();

    }

    @When("I hit an api")
    public void iHitAnApi(Map<String,String> table) {
       response=iHitApi(table);
        response.prettyPrint();
    }

    @Then("I print all contact info who is not associated to company")
    public void iPrintAllContactInfoWhoIsNotAssociatedToCompany() {
        List<Map<String, Object>> contacts = response.jsonPath().get();
        System.out.println(contacts);
        for (Map<String, Object> contact : contacts) {
            if (Objects.isNull(contact.get("contact_company_id"))) {
                List<Map<String, String>> properties = (List<Map<String, String>>) contact.get("properties");
                for (Map<String, String> prop : properties) {
                    if (Objects.nonNull(prop.get("name")) && prop.get("name").equals("first_name")) {
                        System.out.println("Missing company information for contact : " + prop.get("value") + " and Id is : " + contact.get("id"));
                    }
                }
//                System.out.println(properties);
            }
        }
    }

    @Given("I prepare request structure to search contact")
    public void iPrepareRequestStructureToSearchContact(Map<String,String> table) {
        requestSpecification=prepareRequestStructure();
        requestSpecification.queryParams(getQueryParam(table));
    }

    @Then("I verify the contact should be listed in the response")
    public void iVerifyTheContactShouldBeListedInTheResponse(Map<String,String> table) {
        Assert.assertEquals(200, response.statusCode());
        System.out.println("----------------------------------------------------------------------");
        List<Long> contactIds = response.jsonPath().getList("id");
        //System.out.println(contactIds.size());
        List<List<Map<String, String>>> propDetails = response.jsonPath().getList("properties");
        //System.out.println(propDetails.size());
        Assert.assertEquals(contactIds.size(), propDetails.size());

        //iterate each property object
        for (int i = 0; i < contactIds.size(); i++) {
            Long id = contactIds.get(i); // get contact id in list format
            List<Map<String, String>> propObject = propDetails.get(i); // get property object list
            //iterate each property object of contact
            String actualcontactName = null;

            //iterate property object to get company name and website
            for (Map<String, String> prop : propObject) {
                if (prop.get("name").equals("first_name")) {
                    actualcontactName = prop.get("value");
                }
            }
            String[] params = table.get("queryParam").split(",");
            expectedContactName = (params[0].split(":"))[1];

            System.out.println("Id : " + id + "\nExpected Contact Name : " + expectedContactName + "\nActual Contact Name : " + actualcontactName);
            if (actualcontactName.contains(expectedContactName)) {
                System.out.println("Verified Successfully...!!!");
            }
            System.out.println("-------------------------------------------------------------------------------");
        }

    }

    @Given("I prepare request structure to get the contact list")
    public void iPrepareRequestStructureToGetTheContactList() {
        requestSpecification=prepareRequestStructure();
    }

    @Then("I verify the status code should be {int}")
    public void iVerifyTheStatusCodeShouldBe(int statusCode) {
        Assert.assertEquals(statusCode,response.statusCode());
    }

    @Given("I prepare request structure to update the contact by id")
    public void iPrepareRequestStructureToUpdateTheContactById(Map<String,String> table) {
        contactDto = new ContactDto();
       // requestSpecification.get();
        requestSpecification=prepareRequestStructure();
       // requestSpecification.get("contacts/5684773758763008");
       // ContactResponseDto contactResponseDto=response.as(ContactResponseDto.class);
        List<ContactPropertyDto> prop = new ArrayList<>();
        prop.add(utility.setpropertyData(table.get("firstname"), "first_name", "SYSTEM", ""));
        contactDto.setProperties(prop);
        requestSpecification.body(contactDto);


    }

    @Then("I verify the status code should be {int} to update contact by id")
    public void iVerifyTheStatusCodeShouldBeToUpdateContactById(int arg0) {
    }
}
