package com.teko.flightapi.presentation.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingResponseDto {
    private Long id;
    private Long userId;
    private FlightDto flight;
    private LocalDateTime bookingDate;

}