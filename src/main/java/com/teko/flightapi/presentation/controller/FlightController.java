package com.teko.flightapi.presentation.controller;

import com.teko.flightapi.presentation.dto.FlightDto;
import com.teko.flightapi.business.service.FlightService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/flights")
@CrossOrigin(origins = "*")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<FlightDto>> getFlights(
            @RequestParam(required = false) String departureLocation,
            @RequestParam(required = false) String arrivalLocation,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
            @RequestParam(required = false) String airline,
            @RequestParam(required = false) BigDecimal maxPrice) {

        List<FlightDto> flights = flightService.searchFlights(departureLocation, arrivalLocation, departureDate, airline, maxPrice);
        return ResponseEntity.ok(flights);
    }
}