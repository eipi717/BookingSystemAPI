package com.example.BookingSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "booking")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Booking {

    @Column(name = "BOOKING_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_id")
    private BSUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LESSON_id")
    private Lesson lesson;

    @Column(name = "BOOKING_phone")
    private String phone;

    @Column(name = "BOOKING_createts")
    private Long createTs;

    @Column(name = "BOOKING_updatets")
    private Long updateTs;

}
