package com.example.BookingSystem.controller;

import com.example.BookingSystem.DTO.ValidationDTO;
import com.example.BookingSystem.util.JWTUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/token")
public class TokenController {
    private final JWTUtils jwtUtils;

    public TokenController(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @RequestMapping(value = "/validateToken", method = RequestMethod.POST)
    public ResponseEntity<?> validateToken(@RequestBody String token) {

        token = token.replaceAll("\\+", " ").replaceAll("=$", "").replaceAll("Bearer ", "");

        ValidationDTO validationDTO = new ValidationDTO();
        if (token.equals("loggedOut")) { return new ResponseEntity<>(validationDTO, HttpStatus.UNAUTHORIZED); }

        validationDTO.setValid(!jwtUtils.isExpired(token));
        validationDTO.setUserName(jwtUtils.getUserName(token));

        return new ResponseEntity<>(validationDTO, HttpStatus.OK);
    }
}
