package com.teko.flightapi.business.mapper;

import com.teko.flightapi.persistence.entity.Flight;
import com.teko.flightapi.presentation.dto.FlightDto;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class FlightMapper {

    public FlightDto toDto(Flight entity) {
        if (entity == null) return null;
        FlightDto dto = new FlightDto();
        dto.setId(entity.getId());
        dto.setDepartureLocation(entity.getDepartureLocation());
        dto.setArrivalLocation(entity.getArrivalLocation());
        dto.setDepartureDate(entity.getDepartureDate());
        dto.setDepartureTime(entity.getDepartureTime());
        dto.setAirline(entity.getAirline());
        dto.setPrice(entity.getPrice());
        dto.setAvailableTickets(entity.getAvailableTickets());
        return dto;
    }
}