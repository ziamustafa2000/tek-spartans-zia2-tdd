package tek.api.test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import tek.tdd.api.models.EndPoints;
import tek.tdd.base.ApiTestsBase;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.requestSpecification;
import static jdk.internal.org.jline.utils.Colors.s;
@Test
public class getPrimaryAccountTest extends ApiTestsBase {
    public void getAccountAndValidate() {
        RequestSpecification.querryParam("primaryPersonId",10035);
        Response response = requestSpecification.when().get(EndPoints.GET_ACCOUNT.getValue());
        response.then().statusCode(200);
        response.prettyPrint();
        String actualEmail = response.jsonPath().getString("email");
        Assert.assertEquals(actualEmail, "jawid776@gmail.com");



    }
}
