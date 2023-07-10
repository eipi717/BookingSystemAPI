package com.example.BookingSystem.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDTO {

    private Long paymentId;

    private Long bookingId;

    private Double paymentAmount;

    private Long createTs;

    private Long updateTs;
}
