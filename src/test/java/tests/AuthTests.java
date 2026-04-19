package tests;

import base.BaseTest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Epic("Restful Booker API")
@Feature("Authentication")
public class AuthTests extends BaseTest {

    public static String token;

    @Story("Generate Authentication Token")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Sylvia")
    @Description("Verify that valid admin credentials generate a token successfully.")
    @Test(description = "Generate token using valid credentials")
    public void createTokenShouldReturnToken() {

        String requestBody = "{\n" +
                "  \"username\": \"admin\",\n" +
                "  \"password\": \"password123\"\n" +
                "}";

        Allure.step("Prepare authentication request payload");
        Allure.addAttachment("Request Payload", "application/json", requestBody, ".json");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/auth");

        Allure.step("Capture API response");
        Allure.addAttachment("Response Body", "application/json",
                response.getBody().asPrettyString(), ".json");

        Allure.addAttachment("Status Code",
                "text/plain",
                String.valueOf(response.getStatusCode()));

        Assert.assertEquals(response.getStatusCode(), 200,
                "Status code should be 200");

        token = response.jsonPath().getString("token");

        Assert.assertNotNull(token,
                "Token should not be null");
    }
}