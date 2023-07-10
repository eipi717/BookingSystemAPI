package com.example.BookingSystem.controller;

import com.example.BookingSystem.DTO.JWTAuthDTO;
import com.example.BookingSystem.entity.BSUser;
import com.example.BookingSystem.service.UserService;
import com.example.BookingSystem.util.JWTUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final JWTUtils jwtUtils;

    private final UserService userService;

    public UserController(JWTUtils jwtUtils, UserService userService) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password) {

        JWTAuthDTO jwtAuthDTO = new JWTAuthDTO();

        BSUser user = userService.getUserByUsername(username);

        // Invalid username
        if(user == null) {
            jwtAuthDTO.setError("Unknown username. Please try again!");
            return new ResponseEntity<>(jwtAuthDTO, HttpStatus.UNAUTHORIZED);
        }

        // Invalid password
        if(!password.equals(user.getPassword())) {
            jwtAuthDTO.setError("Wrong password. Please try again!");
            return new ResponseEntity<>(jwtAuthDTO, HttpStatus.UNAUTHORIZED);
        }

        String token = jwtUtils.generateToken(user.getUsername());
        jwtAuthDTO.setToken(token);
        return new ResponseEntity<>(jwtAuthDTO, HttpStatus.OK);
    }
}
