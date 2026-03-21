package com.teko.fligthapi.business.service;

import com.teko.fligthapi.business.mapper.BookingMapper;
import com.teko.fligthapi.persistence.dao.BookingRepository;
import com.teko.fligthapi.persistence.dao.FlightRepository;
import com.teko.fligthapi.persistence.dao.UserRepository;
import com.teko.fligthapi.persistence.entity.Booking;
import com.teko.fligthapi.persistence.entity.Flight;
import com.teko.fligthapi.persistence.entity.User;
import com.teko.fligthapi.presentation.dto.BookingRequestDto;
import com.teko.fligthapi.presentation.dto.BookingResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;
    private final BookingMapper bookingMapper;

    public BookingService(BookingRepository bookingRepository, FlightRepository flightRepository,
                          UserRepository userRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
        this.bookingMapper = bookingMapper;
    }

    @Transactional
    public BookingResponseDto createBooking(BookingRequestDto request) {
        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Validierung: Verfügbare Tickets prüfen
        if (flight.getAvailableTickets() <= 0) {
            throw new RuntimeException("No tickets available for this flight");
        }

        // Ticketanzahl reduzieren
        flight.setAvailableTickets(flight.getAvailableTickets() - 1);
        flightRepository.save(flight);

        // Buchung erstellen
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setBookingDate(LocalDateTime.now());

        Booking savedBooking = bookingRepository.save(booking);
        return bookingMapper.toDto(savedBooking);
    }

    public List<BookingResponseDto> getUserBookings(Long userId) {
        return bookingRepository.findByUserIdOrderByBookingDateDesc(userId)
                .stream()
                .map(bookingMapper::toDto)
                .collect(Collectors.toList());
    }
}