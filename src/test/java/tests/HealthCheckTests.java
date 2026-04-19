package tests;

import base.BaseTest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Epic("Restful Booker API")
@Feature("Health Check")
public class HealthCheckTests extends BaseTest {

    @Story("API Availability Check")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Sylvia")
    @Description("Verify that the health check endpoint is available and returns status code 201.")
    @Test(description = "Verify /ping endpoint returns 201")
    public void pingHealthCheckShouldReturn201() {

        Allure.step("Send GET request to /ping endpoint");

        Response response = given()
                .when()
                .get("/ping");

        Allure.step("Capture health check response");

        Allure.addAttachment(
                "Endpoint",
                "text/plain",
                "GET /ping"
        );

        Allure.addAttachment(
                "Status Code",
                "text/plain",
                String.valueOf(response.getStatusCode())
        );

        Allure.addAttachment(
                "Response Body",
                "text/plain",
                response.getBody().asPrettyString()
        );

        Assert.assertEquals(
                response.getStatusCode(),
                201,
                "Health check endpoint should return 201"
        );
    }
}