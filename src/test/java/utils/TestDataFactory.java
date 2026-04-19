package utils;

import models.Booking;
import models.BookingDates;

public class TestDataFactory {

    public static Booking createBookingPayload() {
        return new Booking(
                "Sylvia",
                "Hili",
                1500,
                true,
                new BookingDates("2026-04-20", "2026-04-25"),
                "Breakfast"
        );
    }

    public static Booking createUpdatedBookingPayload() {
        return new Booking(
                "Sylvia Updated",
                "Hili",
                1800,
                false,
                new BookingDates("2026-05-01", "2026-05-06"),
                "Lunch"
        );
    }
}