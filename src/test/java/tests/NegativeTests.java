package tests;

import base.BaseTest;
import clients.BookingClient;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Epic("Negative API Testing")
@Feature("Validation & Security")
public class NegativeTests extends BaseTest {

    private final BookingClient bookingClient = new BookingClient();

    @Test
    @Story("Invalid Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify token is not created when invalid credentials are used")
    public void createToken_InvalidLogin() {

        String body = """
        {
          "username": "wrong",
          "password": "wrong123"
        }
        """;

        Response response = given()
                .contentType("application/json")
                .body(body)
                .when()
                .post("/auth");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.asString().contains("reason"));
    }

    @Test
    @Story("Update Without Token")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify booking update is forbidden without token")
    public void updateBooking_NoToken() {

        String body = """
        {
          "firstname": "Test",
          "lastname": "User"
        }
        """;

        Response response = given()
                .contentType("application/json")
                .body(body)
                .when()
                .put("/booking/1");

        Assert.assertEquals(response.statusCode(), 403);
    }

    @Test
    @Story("Booking Not Found")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify non-existing booking returns 404")
    public void getBooking_NotFound() {

        Response response = bookingClient.getBookingById(99999999);

        Assert.assertEquals(response.statusCode(), 404);
    }
}