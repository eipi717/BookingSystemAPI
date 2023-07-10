package com.example.BookingSystem.converter;

import com.example.BookingSystem.DTO.BookingDTO;
import com.example.BookingSystem.entity.Booking;
import com.example.BookingSystem.helpers.DateTimeHelper;

public class BookingDTOConverter {
    public static final BookingDTO convert(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookingId(booking.getBookingId());
        bookingDTO.setBookingUser(booking.getUser().getUsername());
        bookingDTO.setPhoneNumber(booking.getPhone());
        bookingDTO.setLessonName(booking.getLesson().getLessonName());
        bookingDTO.setLessonPrice(booking.getLesson().getLessonPrice());
        bookingDTO.setCreateTs(DateTimeHelper.tsToDateString(booking.getCreateTs()));
        bookingDTO.setUpdateTs(DateTimeHelper.tsToDateString(booking.getUpdateTs()));

        return bookingDTO;
    }
}
