package util;

import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BaseClass {
    public RequestSpecification requestSpecification = RestAssured.given();
    public Response response;
    public FileInputStream fis;
    public Properties prop;

    public BaseClass(){
        try {
             fis=new FileInputStream("src/test/resources/config.properties");
             prop=new Properties();
             prop.load(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Response iHitApi(Map<String, String> table) {
        String endpoint;
        if (table.get("pathParam") != null && !table.get("pathParam").equals("null")) {
            endpoint = table.get("endpoint") + table.get("pathParam");

        } else {
            endpoint = table.get("endpoint");
        }
        return execute(requestSpecification, table.get("httpMethod"), endpoint);
    }

    public Map<String, String> getQueryParam(Map<String, String> table) {
        Map<String, String> queryParameters = new HashMap<>();
        String[] params = table.get("queryParam").split(",");
        for (String param : params) {
            queryParameters.put(param.split(":")[0], param.split(":")[1]);
        }
        return queryParameters;
    }

    public Response execute(RequestSpecification requestSpecification, String method, String endpoint) {
        try {
            switch (method) {
                case "GET":
                    response = requestSpecification.get(endpoint);
                    break;
                case "POST":
                    response = requestSpecification.post(endpoint);
                    break;
                case "PUT":
                    response = requestSpecification.put(endpoint);
                    break;
                case "PATCH":
                    response = requestSpecification.patch(endpoint);
                    break;
                case "DELETE":
                    response = requestSpecification.delete(endpoint);
                    break;
                default:
                    System.out.println("Invalid HTTP method....!!!");
                    throw new RuntimeException("HTTP method did not match");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public void prepareRequestStructure(Map<String, String> table) {
        String headers = table.get("header");
        requestSpecification.baseUri("https://ushatesting.agilecrm.com")
                .basePath("/dev/api/")
                .headers(headers.split(":")[0], headers.split(":")[1])
                .auth().basic(table.get("username"), table.get("password")).log().all();

    }
    public RequestSpecification prepareRequestStructure(){
                requestSpecification.baseUri(prop.getProperty("baseUri"))
                .basePath(prop.getProperty("basePath"))
                .header("Accept", ContentType.JSON)
                .auth().basic(prop.getProperty("username"), prop.getProperty("password")).log().all();
                return requestSpecification;
    }

}
