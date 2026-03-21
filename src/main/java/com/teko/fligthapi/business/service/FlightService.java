package com.teko.fligthapi.business.service;

import com.teko.fligthapi.business.mapper.FlightMapper;
import com.teko.fligthapi.persistence.dao.FlightRepository;
import com.teko.fligthapi.presentation.dto.FlightDto;
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
        return flightRepository.searchFlights(departure, arrival, date, airline, maxPrice)
                .stream()
                .map(flightMapper::toDto)
                .collect(Collectors.toList());
    }
}