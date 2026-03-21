package com.teko.fligthapi.presentation.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class FlightDto {
    private Long id;
    private String departureLocation;
    private String arrivalLocation;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private String airline;
    private BigDecimal price;
    private Integer availableTickets;

}