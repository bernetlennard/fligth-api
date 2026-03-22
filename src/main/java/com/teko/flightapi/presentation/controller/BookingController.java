package com.teko.flightapi.presentation.controller;

import com.teko.flightapi.business.service.BookingService;
import com.teko.flightapi.business.service.UserService;
import com.teko.flightapi.persistence.entity.User;
import com.teko.flightapi.presentation.dto.BookingRequestDto;
import com.teko.flightapi.presentation.dto.BookingResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    private final BookingService bookingService;
    private final UserService userService;

    public BookingController(BookingService bookingService, UserService userService) {
        this.bookingService = bookingService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(@RequestBody BookingRequestDto request, Principal principal) {
        try {
            BookingResponseDto response = bookingService.createBooking(request, principal.getName());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<BookingResponseDto>> getMyBookings(Principal principal) {
        try{
            List<BookingResponseDto> bookings = bookingService.getUserBookings(principal.getName());
            return ResponseEntity.ok(bookings);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}