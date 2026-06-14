package com.teko.flightapi.presentation.controller;

import com.teko.flightapi.presentation.dto.FlightDto;
import com.teko.flightapi.business.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/flights")
@CrossOrigin(origins = "*")
@Tag(name = "Flight", description = "Endpoints for searching flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @Operation(summary = "Search for flights", description = "Searches for flights based on various criteria.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved flights")
    })
    @GetMapping
    public ResponseEntity<List<FlightDto>> getFlights(
            @Parameter(description = "Departure location of the flight") @RequestParam(required = false) String departureLocation,
            @Parameter(description = "Arrival location of the flight") @RequestParam(required = false) String arrivalLocation,
            @Parameter(description = "Departure date of the flight (YYYY-MM-DD)") @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
            @Parameter(description = "Airline of the flight") @RequestParam(required = false) String airline,
            @Parameter(description = "Maximum price of the flight") @RequestParam(required = false) BigDecimal maxPrice) {

        List<FlightDto> flights = flightService.searchFlights(departureLocation, arrivalLocation, departureDate, airline, maxPrice);
        return ResponseEntity.ok(flights);
    }
}