package com.teko.fligthapi.presentation.controller;

import com.teko.fligthapi.presentation.dto.FlightDto;
import com.teko.fligthapi.business.service.FlightService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/flights")
@CrossOrigin(origins = "*") // Wichtig für die Anbindung der SPA später
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // Endpunkt für Filterparameter
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