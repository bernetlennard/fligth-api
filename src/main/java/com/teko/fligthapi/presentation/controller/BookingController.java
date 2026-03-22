package com.teko.fligthapi.presentation.controller;

import com.teko.fligthapi.business.service.UserService;
import com.teko.fligthapi.presentation.dto.BookingRequestDto;
import com.teko.fligthapi.presentation.dto.BookingResponseDto;
import com.teko.fligthapi.business.service.BookingService;
import com.teko.fligthapi.persistence.dao.UserRepository;
import com.teko.fligthapi.persistence.entity.User;
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
        String userEmail = principal.getName();
        User user = userService.getUserByMail(userEmail);

        // Buchungen für genau diesen User aus der DB holen
        List<BookingResponseDto> bookings = bookingService.getUserBookings(user.getId());
        return ResponseEntity.ok(bookings);
    }
}