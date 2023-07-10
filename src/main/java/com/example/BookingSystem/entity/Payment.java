package com.example.BookingSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "payment")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAYMENT_id")
    private Long paymentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOKING_id")
    private Booking booking;

    @Column(name = "PAYMENT_amount")
    private Double amount;

    @Column(name = "PAYMENT_createts")
    private Long createTs;

    @Column(name = "PAYMENT_updatets")
    private Long updateTs;

}
