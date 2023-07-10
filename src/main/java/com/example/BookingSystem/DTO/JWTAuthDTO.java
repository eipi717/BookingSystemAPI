package com.example.BookingSystem.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTAuthDTO {

    private String token;

    private String error;

}
