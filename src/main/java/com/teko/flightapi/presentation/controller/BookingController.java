package com.teko.flightapi.presentation.controller;

import com.teko.flightapi.business.service.BookingService;
import com.teko.flightapi.presentation.dto.BookingRequestDto;
import com.teko.flightapi.presentation.dto.BookingResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/bookings")
@CrossOrigin(origins = "*")
@Tag(name = "Booking", description = "Endpoints for managing flight bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Operation(summary = "Create a new booking", description = "Creates a new flight booking for the currently authenticated user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request or error during booking creation")
    })
    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(@RequestBody BookingRequestDto request, Principal principal) {
        try {
            BookingResponseDto response = bookingService.createBooking(request, principal.getName());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Get user bookings", description = "Retrieves a list of all bookings for the currently authenticated user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved bookings"),
            @ApiResponse(responseCode = "400", description = "Error retrieving bookings")
    })
    @GetMapping
    public ResponseEntity<List<BookingResponseDto>> getMyBookings(Principal principal) {
        try {
            List<BookingResponseDto> bookings = bookingService.getUserBookings(principal.getName());
            return ResponseEntity.ok(bookings);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}