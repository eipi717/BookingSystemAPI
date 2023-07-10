package com.example.BookingSystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class BSUser {

    @Column(name = "USER_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "USER_username")
    private String username;

    @Column(name = "USER_password")
    private String password;

    @Column(name = "USER_email")
    private String email;

    @Column(name = "USER_createts")
    private Long createTs;

    @Column(name = "USER_updatets")
    private Long updateTs;

}
