package com.teko.flightapi.business.service;

import com.teko.flightapi.business.mapper.FlightMapper;
import com.teko.flightapi.persistence.dao.FlightRepository;
import com.teko.flightapi.presentation.dto.FlightDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    public FlightService(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    public List<FlightDto> searchFlights(String departure, String arrival, LocalDate date, String airline, BigDecimal maxPrice) {

        String depParam = (departure != null && !departure.trim().isEmpty()) ? "%" + departure.toLowerCase() + "%" : null;
        String arrParam = (arrival != null && !arrival.trim().isEmpty()) ? "%" + arrival.toLowerCase() + "%" : null;
        String airParam = (airline != null && !airline.trim().isEmpty()) ? "%" + airline.toLowerCase() + "%" : null;

        return flightRepository.searchFlights(depParam, arrParam, date, airParam, maxPrice)
                .stream()
                .map(flightMapper::toDto)
                .collect(Collectors.toList());
    }
}