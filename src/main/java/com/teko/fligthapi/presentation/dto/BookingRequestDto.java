package com.teko.fligthapi.presentation.dto;

import lombok.Data;

@Data
public class BookingRequestDto {
    private Long userId;
    private Long flightId;

}