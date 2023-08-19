package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParkingDataDTO {

    private Integer id;
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;
    @NotBlank
    private String carLicensePlate;
    @NotBlank
    private String parkingGarageAddress;

}
