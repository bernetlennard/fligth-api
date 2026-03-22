package com.teko.fligthapi.persistence.dao;

import com.teko.fligthapi.persistence.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("SELECT f FROM Flight f WHERE " +
            "(:departure IS NULL OR f.departureLocation = :departure) AND " +
            "(:arrival IS NULL OR f.arrivalLocation = :arrival) AND " +
            "(:date IS NULL OR f.departureDate = :date) AND " +
            "(:airline IS NULL OR f.airline = :airline) AND " +
            "(:maxPrice IS NULL OR f.price <= :maxPrice)")
    List<Flight> searchFlights(@Param("departure") String departure,
                               @Param("arrival") String arrival,
                               @Param("date") LocalDate date,
                               @Param("airline") String airline,
                               @Param("maxPrice") BigDecimal maxPrice);
}