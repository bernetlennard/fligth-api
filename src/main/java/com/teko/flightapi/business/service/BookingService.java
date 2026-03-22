package com.teko.flightapi.business.service;

import com.teko.flightapi.business.mapper.BookingMapper;
import com.teko.flightapi.persistence.dao.BookingRepository;
import com.teko.flightapi.persistence.dao.FlightRepository;
import com.teko.flightapi.persistence.dao.UserRepository;
import com.teko.flightapi.persistence.entity.Booking;
import com.teko.flightapi.persistence.entity.Flight;
import com.teko.flightapi.persistence.entity.User;
import com.teko.flightapi.presentation.dto.BookingRequestDto;
import com.teko.flightapi.presentation.dto.BookingResponseDto;
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
    private final UserService userService;

    public BookingService(BookingRepository bookingRepository, FlightRepository flightRepository,
                          UserRepository userRepository, BookingMapper bookingMapper, UserService userService) {
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
        this.bookingMapper = bookingMapper;
        this.userService = userService;
    }

    @Transactional
    public BookingResponseDto createBooking(BookingRequestDto request, String userEmail) {

        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        User user = userService.getUserByMail(userEmail);

        if (flight.getAvailableTickets() <= 0) {
            throw new RuntimeException("No tickets available for this flight");
        }

        flight.setAvailableTickets(flight.getAvailableTickets() - 1);
        flightRepository.save(flight);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setBookingDate(LocalDateTime.now());

        Booking savedBooking = bookingRepository.save(booking);
        return bookingMapper.toDto(savedBooking);
    }

    public List<BookingResponseDto> getUserBookings(String userEmail) {
        User user = userService.getUserByMail(userEmail);

        return bookingRepository.findByUserIdOrderByBookingDateDesc(user.getId())
                .stream()
                .map(bookingMapper::toDto)
                .collect(Collectors.toList());
    }
}