package com.teko.fligthapi.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String departureLocation;
    private String arrivalLocation;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private String airline;
    private BigDecimal price;
    private Integer availableTickets;

}