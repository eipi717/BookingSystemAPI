package com.example.BookingSystem.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListResponseDTO {
    private long totalResponse;

    private int numberOfPages;

    private List<?> Data;
}
