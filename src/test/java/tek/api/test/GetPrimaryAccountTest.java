package tek.api.test;

import com.aventstack.extentreports.service.ExtentTestManager;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import tek.api.models.EndPoints;
import tek.tdd.base.ApiTestsBase;

public class GetPrimaryAccountTest extends ApiTestsBase {

    @Test
    public void getAccountAndValidate() {
        RequestSpecification requestSpecification = getDefaultRequest();

        requestSpecification.queryParam("primaryPersonId", 10035);

        Response response = requestSpecification.when().get(EndPoints.GET_PRIMARY_ACCOUNT.getValue());
        ExtentTestManager.getTest().info(response.getHeaders().toString());
        response.then().statusCode(200);

        response.prettyPrint();
        String actualEmail = response.jsonPath().getString("email");

        ExtentTestManager.getTest().info(response.asPrettyString());

        Assert.assertEquals(actualEmail, "jawid776@gmail.com");

    }

    //Activity Sending request to get-primary-account with Id does not exist,
    //Validate Error Message
    @Test
    public void validateGetAccountNotExist() {
        Response response = getDefaultRequest()
                .queryParam("primaryPersonId", 252525)
                .when()
                .get(EndPoints.GET_PRIMARY_ACCOUNT.getValue())
                .then()
                .statusCode(404)
                .extract()
                .response();

        ExtentTestManager.getTest().info(response.asPrettyString());

        String errorMessage = response.body().jsonPath().getString("errorMessage");
        Assert.assertEquals(errorMessage, "Account with id 252525 not exist");
    }
}