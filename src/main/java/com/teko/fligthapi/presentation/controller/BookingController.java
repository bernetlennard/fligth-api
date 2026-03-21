package com.teko.fligthapi.presentation.controller;

import com.teko.fligthapi.presentation.dto.BookingRequestDto;
import com.teko.fligthapi.presentation.dto.BookingResponseDto;
import com.teko.fligthapi.business.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // Endpunkt für neue Buchungen [cite: 62]
    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(@RequestBody BookingRequestDto request) {
        try {
            BookingResponseDto response = bookingService.createBooking(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build(); // Basic error handling for now
        }
    }

    // Endpunkt für historische Daten
    @GetMapping("/{userId}")
    public ResponseEntity<List<BookingResponseDto>> getUserBookings(@PathVariable Long userId) {
        List<BookingResponseDto> bookings = bookingService.getUserBookings(userId);
        return ResponseEntity.ok(bookings);
    }
}