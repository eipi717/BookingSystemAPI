package com.example.BookingSystem.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDTO {

    private Long bookingId;

    private String bookingUser;

    private String lessonName;

    private Double lessonPrice;

    private String lessonStartTime;

    private String lessonEndTime;

    private String phoneNumber;

    private String createTs;

    private String updateTs;
}
