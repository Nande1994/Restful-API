package clients;

import io.restassured.response.Response;
import models.Booking;

import static io.restassured.RestAssured.given;

public class BookingClient {

    public Response createBooking(Booking booking) {
        return given()
                .header("Content-Type", "application/json")
                .body(booking)
                .when()
                .post("/booking");
    }

    public Response getBookingById(int bookingId) {
        return given()
                .when()
                .get("/booking/" + bookingId);
    }

    public Response updateBooking(int bookingId, String token, Booking booking) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=" + token)
                .body(booking)
                .when()
                .put("/booking/" + bookingId);
    }

    public Response deleteBooking(int bookingId, String token) {
        return given()
                .header("Cookie", "token=" + token)
                .when()
                .delete("/booking/" + bookingId);
    }
}