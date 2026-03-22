package com.teko.flightapi.business.mapper;

import com.teko.flightapi.persistence.entity.Booking;
import com.teko.flightapi.presentation.dto.BookingResponseDto;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    private final FlightMapper flightMapper;

    public BookingMapper(FlightMapper flightMapper) {
        this.flightMapper = flightMapper;
    }

    public BookingResponseDto toDto(Booking entity) {
        if (entity == null) return null;
        BookingResponseDto dto = new BookingResponseDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setFlight(flightMapper.toDto(entity.getFlight()));
        dto.setBookingDate(entity.getBookingDate());
        return dto;
    }
}