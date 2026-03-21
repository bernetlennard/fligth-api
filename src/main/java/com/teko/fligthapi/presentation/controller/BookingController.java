package com.teko.fligthapi.presentation.controller;

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
    private final UserRepository userRepository; // Brauchen wir, um die User-ID anhand der Email zu finden

    public BookingController(BookingService bookingService, UserRepository userRepository) {
        this.bookingService = bookingService;
        this.userRepository = userRepository;
    }

    // Endpunkt für neue Buchungen [cite: 152]
    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(@RequestBody BookingRequestDto request, Principal principal) {
        try {
            // 1. Email des eingeloggten Users aus dem JWT-Token (Principal) auslesen
            String userEmail = principal.getName();

            // 2. User in der Datenbank suchen
            User user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // 3. WICHTIG: Wir überschreiben die userId im Request mit der ECHTEN ID aus dem Token!
            // (Selbst wenn das Frontend eine falsche ID mitschickt, wird sie hier korrigiert)
            request.setUserId(user.getId());

            // 4. Buchung durchführen
            BookingResponseDto response = bookingService.createBooking(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Endpunkt für historische Daten [cite: 171]
    // Beachte: Das "{userId}" in der URL ist weg! Der User holt automatisch SEINE EIGENEN Buchungen.
    @GetMapping
    public ResponseEntity<List<BookingResponseDto>> getMyBookings(Principal principal) {
        // Gleiches Spiel: Wer fragt an?
        String userEmail = principal.getName();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Buchungen für genau diesen User aus der DB holen
        List<BookingResponseDto> bookings = bookingService.getUserBookings(user.getId());
        return ResponseEntity.ok(bookings);
    }
}