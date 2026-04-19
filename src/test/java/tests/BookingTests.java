package tests;

import base.BaseTest;
import clients.BookingClient;
import io.qameta.allure.*;
import io.restassured.response.Response;
import models.Booking;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestDataFactory;

@Epic("Restful Booker API")
@Feature("Booking Management")
public class BookingTests extends BaseTest {

    private final BookingClient bookingClient = new BookingClient();
    public static int bookingId;

    @Story("Create Booking")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Sylvia")
    @Description("Verify that a booking can be created successfully using valid booking details.")
    @Test(priority = 1, description = "Create booking with valid payload")
    public void createBooking() {
        Booking bookingPayload = TestDataFactory.createBookingPayload();

        Allure.step("Prepare booking creation payload");
        Allure.addAttachment("Create Booking Payload", "text/plain", bookingPayload.toString());

        Response response = bookingClient.createBooking(bookingPayload);
        response.then().log().all();

        Allure.step("Capture create booking response");
        Allure.addAttachment("Create Booking Response", "application/json",
                response.getBody().asPrettyString(), ".json");
        Allure.addAttachment("Status Code", "text/plain",
                String.valueOf(response.getStatusCode()));

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");

        bookingId = response.jsonPath().getInt("bookingid");
        Assert.assertTrue(bookingId > 0, "Booking ID should be generated");

        Assert.assertEquals(response.jsonPath().getString("booking.firstname"), "Sylvia");
        Assert.assertEquals(response.jsonPath().getString("booking.lastname"), "Hili");
    }

    @Story("Retrieve Booking")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Sylvia")
    @Description("Verify that an existing booking can be retrieved successfully by booking ID.")
    @Test(priority = 2, description = "Retrieve booking by valid booking ID")
    public void getBookingById() {
        Response response = bookingClient.getBookingById(bookingId);
        response.then().log().all();

        Allure.step("Capture get booking response");
        Allure.addAttachment("Get Booking Response", "application/json",
                response.getBody().asPrettyString(), ".json");
        Allure.addAttachment("Status Code", "text/plain",
                String.valueOf(response.getStatusCode()));

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        Assert.assertEquals(response.jsonPath().getString("firstname"), "Sylvia");
        Assert.assertEquals(response.jsonPath().getString("lastname"), "Hili");
        Assert.assertEquals(response.jsonPath().getInt("totalprice"), 1500);
    }

    @Story("Update Booking")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Sylvia")
    @Description("Verify that an existing booking can be updated successfully using a valid token.")
    @Test(priority = 3, description = "Update booking with valid token and payload")
    public void updateBooking() {
        Booking updatedBookingPayload = TestDataFactory.createUpdatedBookingPayload();

        Allure.step("Prepare booking update payload");
        Allure.addAttachment("Update Booking Payload", "text/plain", updatedBookingPayload.toString());

        Response response = bookingClient.updateBooking(bookingId, AuthTests.token, updatedBookingPayload);
        response.then().log().all();

        Allure.step("Capture update booking response");
        Allure.addAttachment("Update Booking Response", "application/json",
                response.getBody().asPrettyString(), ".json");
        Allure.addAttachment("Status Code", "text/plain",
                String.valueOf(response.getStatusCode()));

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        Assert.assertEquals(response.jsonPath().getString("firstname"), "Sylvia Updated");
        Assert.assertEquals(response.jsonPath().getInt("totalprice"), 1800);
        Assert.assertEquals(response.jsonPath().getBoolean("depositpaid"), false);
    }

    @Story("Delete Booking")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Sylvia")
    @Description("Verify that an existing booking can be deleted successfully using a valid token.")
    @Test(priority = 4, description = "Delete booking with valid token")
    public void deleteBooking() {
        Response response = bookingClient.deleteBooking(bookingId, AuthTests.token);
        response.then().log().all();

        Allure.step("Capture delete booking response");
        Allure.addAttachment("Delete Booking Response", "text/plain",
                response.getBody().asPrettyString());
        Allure.addAttachment("Status Code", "text/plain",
                String.valueOf(response.getStatusCode()));

        Assert.assertEquals(response.getStatusCode(), 201, "Status code should be 201");
    }

    @Story("Validate Deleted Booking")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Sylvia")
    @Description("Verify that retrieving a deleted booking returns a 404 response.")
    @Test(priority = 5, description = "Verify deleted booking returns 404")
    public void getDeletedBookingShouldReturn404() {
        Response response = bookingClient.getBookingById(bookingId);
        response.then().log().all();

        Allure.step("Capture deleted booking lookup response");
        Allure.addAttachment("Deleted Booking Lookup Response", "text/plain",
                response.getBody().asPrettyString());
        Allure.addAttachment("Status Code", "text/plain",
                String.valueOf(response.getStatusCode()));

        Assert.assertEquals(response.getStatusCode(), 404, "Deleted booking should return 404");
    }
}